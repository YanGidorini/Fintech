package br.com.fintech.controller;

import java.io.IOException;

import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Categoria;
import br.com.fintech.model.Despesa;
import br.com.fintech.model.Receita;
import br.com.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adicionar/despesa")
public class AddDespesaServlet extends HttpServlet {
	private OracleDespesaDAO dao;
	private OracleCategoriaDAO categoriaDao;
	
	public void init() throws ServletException {
		dao = (OracleDespesaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getDespesaDAO();
		categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categorias", categoriaDao.selectAll());
		request.getRequestDispatcher("despesa.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categorias", categoriaDao.selectAll());
		
		try {
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			Categoria categoria = categoriaDao.selectById( Integer.valueOf(request.getParameter("categoria")) );
			
			if (!time.equals("")) {
				date = date + " " + time;
			}
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			
			Despesa despesa = new Despesa(nome, valor, date, categoria, user);
			dao.insert(despesa);
			
			request.setAttribute("msg", "Despesa adicionada!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar a despesa");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar a despesa");
		}
		request.getRequestDispatcher("despesa.jsp").forward(request, response);	
		
	}
}
