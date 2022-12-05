package br.com.fintech.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com o banco de dados
 * @author Yan
 *
 */
public class DBConnectionManager {
	//singleton
	private static DBConnectionManager instance;
	
	private DBConnectionManager(){}
	
	public static DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}
	
	/**
	 * @return variavel do tipo Connection que armazena a conexão com o banco de dados
	 */
	public Connection getConn() {
		Connection conn = null;
		
		try {
	    	String driver = "oracle.jdbc.driver.OracleDriver";
	    	Class.forName(driver); //Registra o Driver
	    	
	    	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	    	String user= "System";
	    	String pswd = "160104";
	        conn = DriverManager.getConnection(url, user, pswd); //Abre uma conexão
		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no Banco de Dados");
			e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	    	System.err.println("O Driver JDBC não foi encontrado!");
	    	e.printStackTrace();
	    }
		
		return conn;
	}
	
}

