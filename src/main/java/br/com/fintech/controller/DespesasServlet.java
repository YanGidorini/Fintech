package br.com.fintech.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Categoria;
import br.com.fintech.model.Despesa;
import br.com.fintech.model.DespesaMes;
import br.com.fintech.model.Receita;
import br.com.fintech.model.ReceitaMes;
import br.com.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/despesas")
public class DespesasServlet extends HttpServlet {

	private OracleDespesaDAO dao;
	private OracleCategoriaDAO categoriaDao;	
	
	public void init() throws ServletException {
		dao = (OracleDespesaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getDespesaDAO();
		categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarDespesas(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void listarDespesas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			List<Despesa> despesas = new ArrayList<Despesa>();	
			List<DespesaMes> listDespesaMes = new ArrayList<>();	
			List<String> years = dao.selectYears(user);
			Map<String, String> meses = dao.getMeses();
			
			for(String year : years) {
				for (String mes : meses.keySet()) {
					despesas = dao.selectAllByUserByMonthByYear(user, mes, year);
					
					if (!despesas.isEmpty()) { 
						String nomeMes = meses.get(mes);
						Double total = dao.sumDespesas(user, mes, year);
						listDespesaMes.add(new DespesaMes(year, nomeMes, despesas, total));
					}
				}
			}
			
			request.setAttribute("categoriaClasses", getClasses());
			request.setAttribute("listDespesaMes", listDespesaMes);
			request.setAttribute("years", years);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível carregar suas despesas");
		} 
		
		request.getRequestDispatcher("despesas.jsp").forward(request, response);

	}
	
	protected Map<String, String> getClasses() {
		List<String> classes = new ArrayList<>(){{
			add("categoria__tag--aluguel");
			add("categoria__tag--alimentacao");
			add("categoria__tag--lazer");
			add("categoria__tag--educacao");
			add("categoria__tag--transporte");
			add("categoria__tag--casa");
			add("categoria__tag--saude");			
		}};
		List<Categoria> categorias = categoriaDao.selectAll();
		Map<String, String> classesCategoria = new LinkedHashMap<>();
	
		for (Categoria categoria : categorias) {
			for (String classe : classes) {
				String nmCategoria = removeAcentos(categoria.getNmCategoria().toLowerCase());
				
				if (classe.contains(nmCategoria)) {
					classesCategoria.put(categoria.getNmCategoria(), classe);
				}	
			}
		}
		
		return classesCategoria;
	}
	
	public String removeAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
