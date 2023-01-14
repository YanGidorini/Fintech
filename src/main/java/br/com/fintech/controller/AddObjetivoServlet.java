package br.com.fintech.controller;

import java.io.IOException;

import br.com.fintech.dao.oracle.OracleCorretoraDAO;
import br.com.fintech.dao.oracle.OracleInvestimentoDAO;
import br.com.fintech.dao.oracle.OracleObjetivoDAO;
import br.com.fintech.dao.oracle.OracleTipoAplicacaoDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Usuario;
import br.com.fintech.model.Corretora;
import br.com.fintech.model.Investimento;
import br.com.fintech.model.Objetivo;
import br.com.fintech.model.TipoAplicacao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adicionar/objetivo")
public class AddObjetivoServlet extends HttpServlet {
	private OracleObjetivoDAO dao;

	
	public void init() throws ServletException {
		dao = (OracleObjetivoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getObjetivoDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("objetivo.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String nome = request.getParameter("name");
			Double valor = Double.valueOf( request.getParameter("valor").replace("R$", "").replace(".", "").replace(",", "."));		
			Double vlAtual = Double.valueOf( request.getParameter("vlAtual").replace("R$", "").replace(".", "").replace(",", "."));	
			String dtFim = request.getParameter("mes") + "/" + request.getParameter("ano");
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			
			if (vlAtual > valor) { throw new Exception("O valor atual não pode ser maior que o valor da meta"); }
			
			Objetivo objetivo = new Objetivo(nome, valor, vlAtual, dtFim, user);
			dao.insert(objetivo);
			
			request.setAttribute("msg", "Objetivo adicionado!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar o Objetivo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Não foi possível adicionar o Objetivo" + ". " + e.getMessage());
		}
		request.getRequestDispatcher("objetivo.jsp").forward(request, response);	
		
	}
}
