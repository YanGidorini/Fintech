package br.com.fintech.factory;

import br.com.fintech.dao.*;
import br.com.fintech.dao.oracle.OracleCategoriaDAO;
import br.com.fintech.dao.oracle.OracleCorretoraDAO;
import br.com.fintech.dao.oracle.OracleDespesaDAO;
import br.com.fintech.dao.oracle.OracleInvestimentoDAO;
import br.com.fintech.dao.oracle.OracleObjetivoDAO;
import br.com.fintech.dao.oracle.OracleReceitaDAO;
import br.com.fintech.dao.oracle.OracleTipoAplicacaoDAO;
import br.com.fintech.dao.oracle.OracleUsuarioDAO;

public class OracleDAOFactory extends DAOFactory{
	
	@Override
	public OracleUsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

	@Override
	public OracleCategoriaDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}

	@Override
	public OracleTipoAplicacaoDAO getTipoAplicacaoDAO() {
		return new OracleTipoAplicacaoDAO();
	}

	@Override
	public OracleCorretoraDAO getCorretoraDAO() {
		return new OracleCorretoraDAO();
	}

	@Override
	public OracleInvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}

	@Override
	public OracleDespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}

	@Override
	public OracleReceitaDAO getReceitaDAO() {
		return new OracleReceitaDAO();
	}

	@Override
	public OracleObjetivoDAO getObjetivoDAO() {
		return new OracleObjetivoDAO();
	}
	 
	
}
