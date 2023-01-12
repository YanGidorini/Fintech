package br.com.fintech.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.fintech.dao.oracle.OracleCorretoraDAO;
import br.com.fintech.dao.oracle.OracleInvestimentoDAO;
import br.com.fintech.dao.oracle.OracleTipoAplicacaoDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Corretora;
import br.com.fintech.model.Investimento;
import br.com.fintech.model.TipoAplicacao;
import br.com.fintech.model.Usuario;


@WebServlet("/investimentos")
public class InvestimentosServlet extends HttpServlet {
	private OracleInvestimentoDAO dao;
	private OracleCorretoraDAO corretoraDao;
	private OracleTipoAplicacaoDAO tipoDao;
	
	public void init() throws ServletException {
		dao = (OracleInvestimentoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getInvestimentoDAO();
		corretoraDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
		tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarInvestimentos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		
		switch(task) {
			case "excluir":
				this.excluir(request, response);
			break;
			
			case "editar":
				this.editar(request, response);
			break;
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			dao.delete(id);
			
			request.setAttribute("msg", "Investimento excluído");
			listarInvestimentos(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.valueOf(request.getParameter("idInv"));
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			String dtRealizacao = request.getParameter("dtRealizacao");
			String dtVencimento = request.getParameter("dtVencimento");
			TipoAplicacao tipo = tipoDao.selectById( Integer.valueOf(request.getParameter("tipo")) );
			Corretora corr = corretoraDao.selectById( Integer.valueOf(request.getParameter("corretora")) );
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			
			Investimento inv = null;	
			if (!dtVencimento.equals("")) {
				inv = new Investimento(id, nome, valor, dtRealizacao, dtVencimento, corr, tipo, user);
			} else {
				inv = new Investimento(id, nome, valor, dtRealizacao, corr, tipo, user);
			}
						
			dao.update(inv);
			request.setAttribute("msg", "Investimento atualizado");
			listarInvestimentos(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	private void listarInvestimentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			List<Investimento> investimentos = dao.selectAllByUser(user);
			
			request.setAttribute("tipos", tipoDao.selectAll());
			request.setAttribute("corretoras", corretoraDao.selectAll());
			request.setAttribute("classesTipo", getClasses());
			request.setAttribute("investimentos", investimentos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível carregar seus investimentos");
		} 
		
		request.getRequestDispatcher("investimentos.jsp").forward(request, response);

	}
	
	protected Map<String, String> getClasses() {
		List<String> classes = new ArrayList<>(){{
			add("categoria__tag--cdb");
			add("categoria__tag--lci");
			add("categoria__tag--tesouro");
			add("categoria__tag--poupanca");
			add("categoria__tag--fiis");
			add("categoria__tag--fundos");
			add("categoria__tag--acoes");			
		}};
		List<TipoAplicacao> tipos = tipoDao.selectAll();
		Map<String, String> classesTipo = new LinkedHashMap<>();
	
		for (TipoAplicacao tipo : tipos) {
			for (String classe : classes) {
				String nmTipo = removeAcentos(tipo.getNmTipo().toLowerCase());
				
				if (nmTipo.equals("lci/lca")) { nmTipo = "lci"; }
				if (nmTipo.equals("tesouro direto")) { nmTipo = "tesouro"; }
					
				if (classe.contains(nmTipo)) {
					classesTipo.put(tipo.getNmTipo(), classe);
				}	
			}
		}
				
		return classesTipo;
	}
	
	public String removeAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
