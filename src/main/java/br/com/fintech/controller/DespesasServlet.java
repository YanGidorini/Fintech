package br.com.fintech.controller;

import java.io.IOException;

import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.factory.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Despesas")
public class DespesasServlet extends HttpServlet {

	private OracleDespesaDAO dao;
	private OracleCategoriaDAO categoriaDao;
	
	public void init() throws ServletException {
		dao = (OracleDespesaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getDespesaDAO();
		categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
