package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Objetivo;
import br.com.fintech.model.Usuario;

public class OracleObjetivoDAO implements DefaultDAO {
	private Connection conn = null;
	
	
	@Override
	public void insert(Object obj) {
		Objetivo objetivo = (Objetivo) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			String sql = "INSERT INTO t_objetivo (cd_objetivo, nm_objetivo, vl_objetivo, vl_atual_objetivo, dt_fim_objetivo, cd_usuario) "
						+ "VALUES (sq_objetivo.nextval, ?, ?, ?, TO_DATE(?, 'MM/YYYY'), ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, objetivo.getNome());
			stmt.setDouble(2, objetivo.getValor());
			stmt.setDouble(3, objetivo.getVlAtualObjetivo());
			stmt.setString(4, objetivo.getDtFimObjetivo());
			stmt.setInt(5, objetivo.getUsuario().getIdUsuario());
			
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
		Objetivo objetivo = (Objetivo) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "UPDATE t_objetivo SET nm_objetivo = ?, vl_objetivo = ?, vl_atual_objetivo = ?,  dt_fim_objetivo = TO_DATE(?, 'MM/YYYY') WHERE cd_objetivo = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, objetivo.getNome());
			stmt.setDouble(2, objetivo.getValor());
			stmt.setDouble(3, objetivo.getVlAtualObjetivo());
			stmt.setString(4, objetivo.getDtFimObjetivo());
			stmt.setInt(5, objetivo.getIdObjetivo());
			
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
			
			String sql = "DELETE FROM t_objetivo WHERE cd_objetivo = ?";
			stmt = conn.prepareStatement(sql);
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
	public List<Objetivo> selectAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Objetivo> objetivoList = new ArrayList<Objetivo>();
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_objetivo, nm_objetivo, vl_objetivo, vl_atual_objetivo, ROUND( (vl_atual_objetivo * 100) / vl_objetivo) as porcentagem, TO_CHAR(dt_fim_objetivo, 'MM/YYYY') as dt_fim_objetivo, cd_usuario "
						+ "FROM t_objetivo ORDER BY cd_objetivo ASC";
			stmt = conn.prepareStatement(sql);
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				int idObjetivo = result.getInt("CD_OBJETIVO");
				String nm = result.getString("NM_OBJETIVO");
				Double valor = result.getDouble("VL_OBJETIVO");
				Double vlAtual = result.getDouble("VL_ATUAL_OBJETIVO");
				Double porcentagem = result.getDouble("porcentagem");
				String dtFim = result.getString("dt_fim_objetivo");
				int idUsuario = result.getInt("CD_USUARIO");
				
				//Instancia um usuario a partir do id obtido
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUsuario);
				
				Objetivo objetivo = new Objetivo(idObjetivo, nm, valor, vlAtual, porcentagem, dtFim, user);
				
				objetivoList.add(objetivo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objetivoList;
	}

	@Override
	public Objetivo selectById(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		Objetivo objetivo = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_objetivo, nm_objetivo, vl_objetivo, vl_atual_objetivo, TO_CHAR(dt_fim_objetivo, 'MM/YYYY') as dt_fim_objetivo, cd_usuario "
						+ "FROM t_objetivo WHERE cd_objetivo = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nm = result.getString("NM_OBJETIVO");
				Double valor = result.getDouble("VL_OBJETIVO");
				Double vlAtual = result.getDouble("VL_ATUAL_OBJETIVO");
				String dtFim = result.getString("dt_fim_objetivo");
				int idUsuario = result.getInt("CD_USUARIO");
				
				//Instancia um usuario a partir do id obtido
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUsuario);
				
				objetivo = new Objetivo(id, nm, valor, vlAtual, dtFim, user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objetivo;
	}
	

	/**
	 * Seleciona todos os objetivos de um usuário específico
	 * @param idUser O código identificador do usuario
	 * @return lista com os objetivos
	 */
	public List<Objetivo> selectAllByUser(Usuario user) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Objetivo> objetivoList = new ArrayList<Objetivo>();
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_objetivo,"
					+ "       	 nm_objetivo,"
					+ "       	 vl_objetivo,"
					+ "       	 vl_atual_objetivo,"
					+ "       	 ROUND( (vl_atual_objetivo * 100) / vl_objetivo,1) as porcentagem,"
					+ "       	 TO_CHAR(dt_fim_objetivo, 'MM/YYYY') as dt_fim,"
					+ "       	 cd_usuario"
					+ "    	FROM t_objetivo"
					+ "    WHERE cd_usuario = ? ORDER BY dt_fim_objetivo ASC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getIdUsuario());
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				int idObjetivo = result.getInt("CD_OBJETIVO");
				String nm = result.getString("NM_OBJETIVO");
				Double valor = result.getDouble("VL_OBJETIVO");
				Double vlAtual = result.getDouble("VL_ATUAL_OBJETIVO");
				Double porcentagem = result.getDouble("porcentagem");
				String dtFim = result.getString("dt_fim");
								
				Objetivo objetivo = new Objetivo(idObjetivo, nm, valor, vlAtual, porcentagem, dtFim, user);
				
				objetivoList.add(objetivo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
				stmt.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return objetivoList;
	}
}
