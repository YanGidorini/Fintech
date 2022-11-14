package br.com.fintech.factory;

public abstract class DAOFactory {
	
	public static final int ORACLE = 1;
	//public static final int MY_SQL= 2;
	
	private static DAOFactory oracleDAOFactory;
	//private static DAOFactory mySqlDAOFactory;
	
	public static DAOFactory getDAOFactory(int db) {
		switch (db) {
		case 1:
			if (oracleDAOFactory == null) {
				oracleDAOFactory = new OracleDAOFactory();
			}
			return oracleDAOFactory;
		default:
			return null;
		}
	}
	
	public abstract Object getUsuarioDAO();
	public abstract Object getCategoriaDAO();
	public abstract Object getTipoAplicacaoDAO();
	public abstract Object getCorretoraDAO();
	public abstract Object getInvestimentoDAO();
	public abstract Object getDespesaDAO();
	public abstract Object getReceitaDAO();
	public abstract Object getObjetivoDAO();
	
}
