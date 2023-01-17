package br.com.fintech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import br.com.fintech.dao.oracle.OracleUsuarioDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Usuario;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private OracleUsuarioDAO dao;

	public void init() throws ServletException {
		dao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		
		switch(task) {
			case "cadastrar":
				request.getRequestDispatcher("cadastroUsuario.jsp").forward(request, response);			
				break;
				
			case "login":
				request.getRequestDispatcher("login.jsp").forward(request, response);			
				break;
				
			case "logout":
				this.logout(request, response);
				break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		
		switch(task) {
		case "cadastrar":
			this.cadastrar(request, response);			
			break;
		
		case "login":
			this.login(request, response);
			break;
		}
		
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("pswd");
			String nome = request.getParameter("nome");
			String dtNasc = request.getParameter("dtNasc");
			String genero = request.getParameter("genero");
			Usuario user = new Usuario(nome, dtNasc, email, senha, genero);
			
			dao.insert(user);
			
			request.setAttribute("msg", "Cadastro realizado!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível realizar o cadastro");
		}
		
		request.getRequestDispatcher("cadastroUsuario.jsp").forward(request, response);			
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("pswd");
			Usuario user = new Usuario(email, senha);
			
			int idUser = dao.authenticate(user);
			
			if(idUser != 0) {
				//define a sessão
				user = dao.selectById(idUser);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				response.sendRedirect(request.getContextPath() + "/dashboard");
			} else {
				//não encontrou registro
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
				request.getRequestDispatcher("login.jsp").forward(request, response);	
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível entrar na conta devido um erro no servidor. Tente novamente mais tarde");
		}
				
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}
	
	
}
