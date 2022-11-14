package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.model.Categoria;

public class OracleCategoriaDAO implements DefaultDAO  {
	private Connection conn = null;

	@Override
	public void insert(Object obj) {
		Categoria categoria = (Categoria) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("INSERT INTO t_categoria (cd_categoria, nm_categoria) VALUES (sq_categoria.nextval, ?)");
			stmt.setString(1, categoria.getNmCategoria());
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
		Categoria categoria = (Categoria) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("UPDATE t_categoria SET nm_categoria = ? WHERE cd_categoria = ?");
			stmt.setString(1, categoria.getNmCategoria());
			stmt.setInt(2, categoria.getIdCategoria());
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
			
			stmt = conn.prepareStatement("DELETE FROM t_categoria WHERE cd_categoria = ?");
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
	public List<Categoria> selectAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Categoria> categoryList = new ArrayList<Categoria>();
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_categoria, nm_categoria FROM t_categoria ORDER BY cd_categoria ASC");
			result = stmt.executeQuery();
			
			while(result.next()) {
				int id = result.getInt("CD_CATEGORIA");
				String nmCategoria = result.getString("NM_CATEGORIA");
				Categoria categoria = new Categoria(id, nmCategoria);
				
				categoryList.add(categoria);
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
		return categoryList;
	}

	@Override
	public Categoria selectById(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		Categoria categoria = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			stmt = conn.prepareStatement("SELECT cd_categoria, nm_categoria FROM t_categoria WHERE cd_categoria = ?");
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nmCategoria = result.getString("NM_CATEGORIA");
				categoria = new Categoria(id, nmCategoria);
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
		return categoria;


	}

}
