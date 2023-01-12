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
		request.setAttribute("dao", dao);
		listarReceitas(request, response);
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
			
			request.setAttribute("msg", "Receita excluída");
			listarReceitas(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			int id = Integer.valueOf(request.getParameter("idReceita"));
			
			if (!time.equals("")) {
				date = date + " " + time;
			}
			
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			Receita receita = new Receita(id, nome, valor, date, user);
			dao.update(receita);
			
			request.setAttribute("msg", "Receita atualizada");
			listarReceitas(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	private void listarReceitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			List<Receita> receitas = new ArrayList<Receita>();	
			List<ReceitaMes> listReceitaMes = new ArrayList<>();	
			List<String> years = dao.selectYears(user);
			Map<String, String> meses = dao.getMeses();
			
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
			
			request.setAttribute("listReceitaMes", listReceitaMes);
			request.setAttribute("years", years);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível carregar suas receitas");
		} 
		
		request.getRequestDispatcher("receitas.jsp").forward(request, response);

	}
}
