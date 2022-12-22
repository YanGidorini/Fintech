package br.com.fintech.controller;

import java.io.IOException;

import br.com.fintech.dao.oracle.OracleCorretoraDAO;
import br.com.fintech.dao.oracle.OracleInvestimentoDAO;
import br.com.fintech.dao.oracle.OracleTipoAplicacaoDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Usuario;
import br.com.fintech.model.Corretora;
import br.com.fintech.model.Investimento;
import br.com.fintech.model.TipoAplicacao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adicionar/investimento")
public class AddInvestimentoServlet extends HttpServlet {
	private OracleInvestimentoDAO dao;
	private OracleTipoAplicacaoDAO tipoDao;
	private OracleCorretoraDAO corrDao;
	
	public void init() throws ServletException {
		dao = (OracleInvestimentoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getInvestimentoDAO();
		tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();
	    corrDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tipos", tipoDao.selectAll());
		request.setAttribute("corretoras", corrDao.selectAll());
		request.getRequestDispatcher("investimento.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tipos", tipoDao.selectAll());
		request.setAttribute("corretoras", corrDao.selectAll());
		
		try {
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			String dtRealizacao = request.getParameter("dtRealizacao");
			String dtVencimento = request.getParameter("dtVencimento");
			TipoAplicacao tipo = tipoDao.selectById( Integer.valueOf(request.getParameter("tipo")) );
			Corretora corr = corrDao.selectById( Integer.valueOf(request.getParameter("corretora")) );
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			
			Investimento inv = null;	
			if (!dtVencimento.equals("")) {
				inv = new Investimento(nome, valor, dtRealizacao, dtVencimento, corr, tipo, user);
			} else {
				inv = new Investimento(nome, valor, dtRealizacao, corr, tipo, user);
			}
						
			dao.insert(inv);
			
			request.setAttribute("msg", "Investimento adicionado!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar o investimento");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar o investimento");
		}
		request.getRequestDispatcher("investimento.jsp").forward(request, response);	
		
	}
}
