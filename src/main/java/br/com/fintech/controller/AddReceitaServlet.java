package br.com.fintech.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.fintech.dao.oracle.OracleReceitaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Receita;
import br.com.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adicionar/receita")
public class AddReceitaServlet extends HttpServlet {
	
	private OracleReceitaDAO dao;
	
	public void init() throws ServletException {
		dao = (OracleReceitaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getReceitaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("receita.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			if (!time.equals("")) {
				date = date + " " + time;
			}
			
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			Receita receita = new Receita(nome, valor, date, user);
			dao.insert(receita);
			
			request.setAttribute("msg", "Receita adicionada!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar a receita");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar a receita");
		}
		request.getRequestDispatcher("receita.jsp").forward(request, response);	
		
	}
}
