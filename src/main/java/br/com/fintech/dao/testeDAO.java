package br.com.fintech.dao;

import java.text.ParseException;
import java.util.List;

import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleCorretoraDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.dao.oracle.OracleInvestimentoDAO;
import br.com.fintech.dao.oracle.OracleObjetivoDAO;
import br.com.fintech.dao.oracle.OracleReceitaDAO;
import br.com.fintech.dao.oracle.OracleTipoAplicacaoDAO;
import br.com.fintech.dao.oracle.OracleUsuarioDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.*;

public class testeDAO {

	public static void main(String[] args) throws ParseException {		
		//Instanciando todos os DAOs
		OracleInvestimentoDAO invDao = (OracleInvestimentoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getInvestimentoDAO();
		OracleCorretoraDAO corrDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
		OracleTipoAplicacaoDAO tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();	
		OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
		OracleObjetivoDAO objetivoDao = (OracleObjetivoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getObjetivoDAO();
		OracleCategoriaDAO categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
		OracleReceitaDAO receitaDao = (OracleReceitaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getReceitaDAO();
		OracleDespesaDAO despesaDao = (OracleDespesaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getDespesaDAO();
	
		/*TESTANDO DAO USUARIO*/
		
		//Insert usuario
//		String nm = "Henrique Jorge Silva";
//		String dtNasc = "1980-05-23";
//		String email = "rique@gmail.com";
//		String senha = "123456";
//		String genero = "M";
//		Usuario user = new Usuario(nm, dtNasc, email, senha, genero);
//		
//		userDao.insert(user);
		
		//Select by id e Update usuario
//		Usuario user = userDao.selectById(6);
//		System.out.println(user); //antes
//		
//		user.setNmUsuario("Rique Silva");
//		user.setDtNascimento("1980-01-01");
//		user.setEmail("jorge@gmail.com");
//		user.setGenero("F");
//		
//		userDao.update(user);
//		
//		System.out.println(userDao.selectById(6)); //depois
		
		//Metodo para atualizar senha
//		Usuario user = userDao.selectById(6);
//		userDao.updatePswd(user, "123456", "Charuto", "Charuto");
		
		//Delete usuario
//		userDao.delete(6);
		
		//selectAll usuario
//		List<Usuario> usuarios = userDao.selectAll();
//		
//		for(Usuario item : usuarios) {
//			System.out.println(item);
//		}
		
		/*TESTANDO DESPESA*/
		
		//insert
//		Usuario user = userDao.selectById(3);
//		
//		String nm = "Ingles";
//		Double vl = 100.00;
//		String dtHr = "2022-10-18 18:20";
//		Categoria categoria = categoriaDao.selectById(4);
//		Despesa d = new Despesa(nm, vl, dtHr, categoria, user);
//		
//		despesaDao.delete(6);

//		System.out.println(despesaDao.sumDespesasByUserByMonthByYear(2, "10", "2022"));

		
//		for(Despesa item : despesas) {
//			System.out.println(item);
//		}

		/*TESTANDO RECEITA*/
		Usuario user = userDao.selectById(2);
		
		String nm = "Devedores2";
		Double vl = 15000.00;
		String dtHr = "2022-10-20 01:00";
		Receita receita = new Receita(nm, vl, dtHr, user);
		
		List<Receita> receitas = receitaDao.selectAllByUserByMonthByYear(2, "10", "2022");
		
		for(Receita item : receitas) {
			System.out.println(item);
		}
		
		
		
		 
		
		
		
	}

}
