package br.com.fintech.factory;

/**
 * Uma fábrica de DAOs (Data Access Object)
 */
public abstract class DAOFactory {
	
	/**
	 * Constante que indica o banco de dados
	 */
	public static final int ORACLE = 1;
	//public static final int MY_SQL= 2;
	
	/**
	 * Atributo que armazena uma instancia da fábrica oracle de DAOs
	 */
	private static DAOFactory oracleDAOFactory;
	//private static DAOFactory mySqlDAOFactory;
	
	/**
	 * Seleciona a fábrica DAO específica de um banco de dados. Uma nova instancia da fábrica só é gerada se não houver outra já instanciada (singleton)
	 * @param db A constante que indica o banco de dados
	 * @return a instancia do DAO do banco de dados
	 */
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
	
	/**
	 * @return A instância DAO para Usuario
	 */
	public abstract Object getUsuarioDAO();
	/**
	 * @return A instância DAO para Categoria
	 */
	public abstract Object getCategoriaDAO();
	/**
	 * @return A instância DAO para TipoAplicacao
	 */
	public abstract Object getTipoAplicacaoDAO();
	/**
	 * @return A instância DAO para Corretora
	 */
	public abstract Object getCorretoraDAO();
	/**
	 * @return A instância DAO para Investimento
	 */
	public abstract Object getInvestimentoDAO();
	/**
	 * @return A instância DAO para Despesa
	 */
	public abstract Object getDespesaDAO();
	/**
	 * @return A instância DAO para Receita
	 */
	public abstract Object getReceitaDAO();
	/**
	 * @return A instância DAO para Objetivo
	 */
	public abstract Object getObjetivoDAO();
	
}
