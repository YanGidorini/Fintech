package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.model.TipoAplicacao;

public class OracleTipoAplicacaoDAO implements DefaultDAO {
	private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		TipoAplicacao tipo = (TipoAplicacao) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("INSERT INTO t_tipo_aplicacao (cd_tipo, nm_tipo) VALUES (sq_tipo.nextval, ?)");
			stmt.setString(1, tipo.getNmTipo());
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
		TipoAplicacao tipo = (TipoAplicacao) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("UPDATE t_tipo_aplicacao SET nm_tipo = ? WHERE cd_tipo = ?");
			stmt.setString(1, tipo.getNmTipo());
			stmt.setInt(2, tipo.getIdTipo());
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
			
			stmt = conn.prepareStatement("DELETE FROM t_tipo_aplicacao WHERE cd_tipo = ?");
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
	public List<TipoAplicacao> selectAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<TipoAplicacao> tipoList = new ArrayList<TipoAplicacao>();
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_tipo, nm_tipo FROM t_tipo_aplicacao ORDER BY cd_tipo ASC");
			result = stmt.executeQuery();
			
			while(result.next()) {
				int id = result.getInt("CD_TIPO");
				String nmTipo = result.getString("NM_TIPO");
				TipoAplicacao tipo = new TipoAplicacao(id, nmTipo);
				
				tipoList.add(tipo);
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
		return tipoList;
	}

	@Override
	public TipoAplicacao selectById(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		TipoAplicacao tipo = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_tipo, nm_tipo FROM t_tipo_aplicacao WHERE cd_tipo = ?");
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nmTipo = result.getString("NM_TIPO");
				tipo = new TipoAplicacao(id, nmTipo);
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
		return tipo;
	}

}
