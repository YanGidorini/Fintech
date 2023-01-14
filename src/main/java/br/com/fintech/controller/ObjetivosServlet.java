package br.com.fintech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.fintech.dao.oracle.OracleObjetivoDAO;
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

@WebServlet("/objetivos")
public class ObjetivosServlet extends HttpServlet {
	private OracleObjetivoDAO dao;
	
	public void init() throws ServletException {
		dao = (OracleObjetivoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getObjetivoDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarObjetivos(request, response);
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

	private void editar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private void listarObjetivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");

			request.setAttribute("objetivos", dao.selectAllByUser(user));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível carregar seus objetivos");
		} 
		
		request.getRequestDispatcher("objetivos.jsp").forward(request, response);
	}
}
