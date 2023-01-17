package br.com.fintech.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.dao.oracle.OracleReceitaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Categoria;
import br.com.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private OracleReceitaDAO daoReceita;
	private OracleDespesaDAO daoDespesa;
	private OracleCategoriaDAO categoriaDao;
	private Map<String, String> convertMes = new LinkedHashMap<>() {{
		put("0", "01");
		put("1", "02");
		put("2", "03");
		put("3", "04");
		put("4", "05");
		put("5", "06");
		put("6", "07");
		put("7", "08");
		put("8", "09");
		put("9", "10");
		put("10", "11");
		put("11", "12");
		
	}};

	public void init() throws ServletException {
		daoReceita = (OracleReceitaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getReceitaDAO();
		daoDespesa = (OracleDespesaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getDespesaDAO();
		categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("user");
			Calendar calendar = Calendar.getInstance();
			String mes = convertMes.get(String.valueOf(calendar.get(Calendar.MONTH)));			
			String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("pt","BR"));
			String firstLetter = month.substring(0,1);
			String monthName = firstLetter.toUpperCase() + month.substring(1);
			String ano = String.valueOf(calendar.get(Calendar.YEAR));
			Double sumReceitas = daoReceita.sumReceitas(user, mes, ano);
			Double sumDespesas = daoDespesa.sumDespesas(user, mes, ano);
			
			request.setAttribute("ano", ano);
			request.setAttribute("nomeMes", monthName);
			request.setAttribute("sumReceitas", sumReceitas);
			request.setAttribute("sumDespesas", sumDespesas);
			request.setAttribute("saldo", sumReceitas - sumDespesas);
			request.setAttribute("receitaRecente", daoReceita.lastReceita(user));
			request.setAttribute("despesaRecente", daoDespesa.lastDespesa(user));
			request.setAttribute("categoriaClasses", getClasses());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
