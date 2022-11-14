package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.model.Categoria;
import br.com.fintech.model.Receita;
import br.com.fintech.model.Usuario;

public class OracleReceitaDAO implements DefaultDAO {
private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		Receita receita = (Receita) obj;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "INSERT INTO t_receita (cd_receita, nm_receita, vl_receita, dt_receita, cd_usuario) "
						+ "VALUES (sq_receita.nextval, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, receita.getNome());
			stmt.setDouble(2, receita.getValor());
			stmt.setString(3, receita.getDtReceita());
			stmt.setInt(4, receita.getUsuario().getIdUsuario());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(Object obj) {
		Receita receita = (Receita) obj;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "UPDATE t_receita SET "
						+ "nm_receita = ?,"
						+ "vl_receita = ?,"
						+ "dt_receita = TO_DATE(?, 'YYYY-MM-DD HH24:MI') "
						+ "WHERE cd_receita = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, receita.getNome());
			stmt.setDouble(2, receita.getValor());
			stmt.setString(3, receita.getDtReceita());
			stmt.setInt(4, receita.getIdReceita());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void delete(int id) {
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
						
			stmt = conn.prepareStatement("DELETE FROM t_receita WHERE cd_receita = ?");
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Receita> selectAll() {
		List<Receita> receitaList = new ArrayList<Receita>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_receita, nm_receita, vl_receita, TO_CHAR(dt_receita, 'YYYY-MM-DD HH24:MI') as dt_receita, cd_usuario "
						+ "FROM t_receita";
			stmt = conn.prepareStatement(sql);
			
			result = stmt.executeQuery();
	
			while(result.next()) {
				int idReceita = result.getInt("CD_RECEITA");
				String nm = result.getString("NM_RECEITA");
				Double vl = result.getDouble("VL_RECEITA");
				String dtHr = result.getString("DT_RECEITA");
				int idUser = result.getInt("CD_USUARIO");
			
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				Receita receita = new Receita(idReceita, nm, vl, dtHr, user);
				
				receitaList.add(receita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return receitaList;
	}

	@Override
	/**
	 * @return id O código identificador da despesa
	 */
	public Receita selectById(int id) {
		Receita receita = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_receita, nm_receita, vl_receita, TO_CHAR(dt_receita, 'YYYY-MM-DD HH24:MI') as dt_receita, cd_usuario  "
						+ "FROM t_receita WHERE cd_receita = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nm = result.getString("NM_RECEITA");
				Double vl = result.getDouble("VL_RECEITA");
				String dtHr = result.getString("DT_RECEITA");
				int idUser = result.getInt("CD_USUARIO");
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				receita = new Receita(id, nm, vl, dtHr, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return receita;
	}
	
	/**
	 * Método usado para recuperar todas as receitas de um usuário para mostrar na tela, formatado o máximo possível
	 * @param idUser O código identificador do usuario
	 * @param mes O mes das despesas
	 * @param ano O ano das despesas
	 * @return Lista das despesas de um usuario especifico
	 */
	public List<Receita> selectAllByUserByMonthByYear(int idUser, String mes, String ano) {
		List<Receita> receitaList = new ArrayList<Receita>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_receita, "
							  + "nm_receita, "
							  + "vl_receita, "
							  + "TRIM(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TO_CHAR(dt_receita, 'Dy, DD \"de\" Month', 'NLS_DATE_LANGUAGE=PORTUGUESE'), 'Dom', 'Domingo'), 'Seg','Segunda-feira'), 'Ter','Terça-feira'), 'Qua','Quarta-feira'), 'Qui','Quinta-feira'), 'Sex','Sexta-feira'), 'Sáb','Sábado')) as dt_extenso, "
							  + "TO_CHAR(dt_receita, 'HH24:MI') as hr_receita "
						+ "FROM t_receita "
						+ "WHERE cd_usuario = ? AND TO_CHAR(dt_receita, 'MM') = ? AND TO_CHAR(dt_receita, 'YYYY') = ? "
						+ "ORDER BY dt_receita DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUser);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			
			result = stmt.executeQuery();
	
			while(result.next()) {
				int idReceita = result.getInt("CD_RECEITA");
				String nm = result.getString("NM_RECEITA");
				Double vl = result.getDouble("VL_RECEITA");
				String dtExtenso = result.getString("DT_EXTENSO");
				String hr = result.getString("hr_receita");
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				Receita receita = new Receita(idReceita, nm, vl, dtExtenso, hr, user);
				
				receitaList.add(receita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return receitaList;
		
	};
		
	/**
	 * Soma todas as receitas de um usuário durante um mês específico
	 * @param idUser O código identificador do usuario
	 * @param mes O mes em questão
	 * @return A soma
	 */
	public Double sumReceitasByUserByMonthByYear(int idUser, String mes, String ano) {
		Double sum = 0.0;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT SUM(vl_receita) as sum_receita FROM t_receita WHERE cd_usuario = ? AND TO_CHAR(dt_receita, 'MM') = ? AND TO_CHAR(dt_receita, 'YYYY') = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUser);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			
			result = stmt.executeQuery();
	
			if(result.next()) {
				sum = result.getDouble("sum_receita");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return sum;	
	};
	
}
