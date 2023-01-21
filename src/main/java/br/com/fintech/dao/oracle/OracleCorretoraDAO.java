package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.model.Corretora;

public class OracleCorretoraDAO implements DefaultDAO {
	/**
	 * Variável que armazena conexão
	 */
	private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		Corretora corretora = (Corretora) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("INSERT INTO t_corretora (cd_corretora, nm_corretora) VALUES (sq_corretora.nextval, ?)");
			stmt.setString(1, corretora.getNmCorretora());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void update(Object obj) {
		Corretora corretora = (Corretora) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("UPDATE t_corretora SET nm_corretora = ? WHERE cd_corretora = ?");
			stmt.setString(1, corretora.getNmCorretora());
			stmt.setInt(2, corretora.getIdCorretora());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("DELETE FROM t_corretora WHERE cd_corretora = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public List<Corretora> selectAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Corretora> corretoraList = new ArrayList<Corretora>();
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_corretora, nm_corretora FROM t_corretora ORDER BY cd_corretora ASC");
			result = stmt.executeQuery();
			
			while(result.next()) {
				int id = result.getInt("CD_CORRETORA");
				String nmCorretora = result.getString("NM_CORRETORA");
				Corretora corretora = new Corretora(id, nmCorretora);
				
				corretoraList.add(corretora);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return corretoraList;
	}

	@Override
	public Corretora selectById(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		Corretora corretora = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_corretora, nm_corretora FROM t_corretora WHERE cd_corretora = ?");
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nmCorretora = result.getString("NM_CORRETORA");
				corretora = new Corretora(id, nmCorretora);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return corretora;

	}

}
