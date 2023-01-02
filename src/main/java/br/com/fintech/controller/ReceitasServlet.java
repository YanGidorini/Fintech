package br.com.fintech.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.fintech.dao.oracle.OracleReceitaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Receita;
import br.com.fintech.model.ReceitaMes;
import br.com.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/receitas")
public class ReceitasServlet extends HttpServlet {
	private OracleReceitaDAO dao;
	
	public void init() throws ServletException {
		dao = (OracleReceitaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getReceitaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listarReceitas(request);
		} catch (Exception e) {
			request.setAttribute("msg", "Não foi possível carregar suas receitas");
		}
		request.getRequestDispatcher("receitas.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	private void listarReceitas(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		List<Receita> receitas = new ArrayList<Receita>();	
		List<ReceitaMes> listReceitaMes = new ArrayList<>();	
		List<String> years = dao.selectYears(user);
		Map<String, String> meses = dao.getMeses();
		//List<String> meses = dao.getMonths();
		
		for(String year : years) {
			for (String mes : meses.keySet()) {
				receitas = dao.selectAllByUserByMonthByYear(user, mes, year);
				
				if (!receitas.isEmpty()) { 
					String nomeMes = meses.get(mes);
					Double total = dao.sumReceitas(user, mes, year);
					listReceitaMes.add(new ReceitaMes(year, nomeMes, receitas, total));
				}
			}
		}
		
		request.setAttribute("dao", dao);
		request.setAttribute("listReceitaMes", listReceitaMes);
		request.setAttribute("years", years);
	}
}
