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
import br.com.fintech.model.Despesa;
import br.com.fintech.model.Usuario;

public class OracleDespesaDAO implements DefaultDAO {
	private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		Despesa despesa = (Despesa) obj;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "INSERT INTO t_despesa (cd_despesa, nm_despesa, vl_despesa, dt_hr_despesa, cd_categoria, cd_usuario) "
						+ "VALUES (sq_despesa.nextval, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, despesa.getNome());
			stmt.setDouble(2, despesa.getValor());
			stmt.setString(3, despesa.getDtHrDespesa());
			stmt.setInt(4, despesa.getCategoria().getIdCategoria());
			stmt.setInt(5, despesa.getUsuario().getIdUsuario());
			
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
		Despesa despesa = (Despesa) obj;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "UPDATE t_despesa SET "
						+ "nm_despesa = ?,"
						+ "vl_despesa = ?,"
						+ "dt_hr_despesa = TO_DATE(?, 'YYYY-MM-DD HH24:MI'),"
						+ "cd_categoria = ?,"
						+ "cd_usuario = ? "
						+ "WHERE cd_despesa = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, despesa.getNome());
			stmt.setDouble(2, despesa.getValor());
			stmt.setString(3, despesa.getDtHrDespesa());
			stmt.setInt(4, despesa.getCategoria().getIdCategoria());
			stmt.setInt(5, despesa.getUsuario().getIdUsuario());
			stmt.setInt(6, despesa.getIdDespesa());
			
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
						
			stmt = conn.prepareStatement("DELETE FROM t_despesa WHERE cd_despesa = ?");
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
	public List<Despesa> selectAll() {
		List<Despesa> despesaList = new ArrayList<Despesa>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_despesa, nm_despesa, vl_despesa, TO_CHAR(dt_hr_despesa, 'YYYY-MM-DD HH24:MI') as dt_hr_despesa, cd_categoria, cd_usuario "
						+ "FROM t_despesa";
			stmt = conn.prepareStatement(sql);
			
			result = stmt.executeQuery();
	
			while(result.next()) {
				int idDespesa = result.getInt("CD_DESPESA");
				String nm = result.getString("NM_DESPESA");
				Double vl = result.getDouble("VL_DESPESA");
				String dtHr = result.getString("DT_HR_DESPESA");
				int idCategoria = result.getInt("CD_CATEGORIA");
				int idUser = result.getInt("CD_USUARIO");
				
				OracleCategoriaDAO categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
				Categoria categoria = categoriaDao.selectById(idCategoria);
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				Despesa despesa = new Despesa(idDespesa, nm, vl, dtHr, categoria, user);
				
				despesaList.add(despesa);
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
		
		return despesaList;
	}

	@Override
	/**
	 * @return id O código identificador da despesa
	 */
	public Despesa selectById(int id) {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_despesa, nm_despesa, vl_despesa, TO_CHAR(dt_hr_despesa, 'YYYY-MM-DD HH24:MI') as dt_hr_despesa, cd_categoria, cd_usuario "
						+ "FROM t_despesa WHERE cd_despesa = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nm = result.getString("NM_DESPESA");
				Double vl = result.getDouble("VL_DESPESA");
				String dtHr = result.getString("DT_HR_DESPESA");
				int idCategoria = result.getInt("CD_CATEGORIA");
				int idUser = result.getInt("CD_USUARIO");
				
				OracleCategoriaDAO categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
				Categoria categoria = categoriaDao.selectById(idCategoria);
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				despesa = new Despesa(id, nm, vl, dtHr, categoria, user);
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
		
		return despesa;
	}
	
	/**
	 * Método usado para recuperar todas as despesas de um usuário para mostrar na tela, formatado o máximo possível
	 * @param idUser O código identificador do usuario
	 * @param mes O mes das despesas
	 * @param ano O ano das despesas
	 * @return Lista das despesas de um usuario especifico
	 */
	public List<Despesa> selectAllByUserByMonthByYear(int idUser, String mes, String ano) {
		List<Despesa> despesaList = new ArrayList<Despesa>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_despesa, "
							  + "nm_despesa, "
							  + "vl_despesa, "
							  + "TRIM(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TO_CHAR(dt_hr_despesa, 'Dy, DD \"de\" Month', 'NLS_DATE_LANGUAGE=PORTUGUESE'), 'Dom', 'Domingo'), 'Seg','Segunda-feira'), 'Ter','Terça-feira'), 'Qua','Quarta-feira'), 'Qui','Quinta-feira'), 'Sex','Sexta-feira'), 'Sáb','Sábado')) as dt_extenso, "
							  + "TO_CHAR(dt_hr_despesa, 'HH24:MI') as hr_despesa, "
							  + "cd_categoria "
						+ "FROM t_despesa "
						+ "WHERE cd_usuario = ? AND TO_CHAR(dt_hr_despesa, 'MM') = ? AND TO_CHAR(dt_hr_despesa, 'YYYY') = ? "
						+ "ORDER BY dt_hr_despesa DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUser);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			
			result = stmt.executeQuery();
	
			while(result.next()) {
				int idDespesa = result.getInt("CD_DESPESA");
				String nm = result.getString("NM_DESPESA");
				Double vl = result.getDouble("VL_DESPESA");
				String dtExtenso = result.getString("DT_EXTENSO");
				String hr = result.getString("hr_despesa");
				int idCategoria = result.getInt("CD_CATEGORIA");
				
				OracleCategoriaDAO categoriaDao = (OracleCategoriaDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCategoriaDAO();
				Categoria categoria = categoriaDao.selectById(idCategoria);
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				Despesa despesa = new Despesa(idDespesa, nm, vl, dtExtenso, hr, categoria, user);
				
				despesaList.add(despesa);
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
		
		return despesaList;
		
	};
	
	/**
	 * Soma todas as despesas de um usuário
	 * @param idUser O código identificador do usuario
	 * @return A soma
	 */
	public Double sumAllDespesasByUser(int idUser) {
		Double sum = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT SUM(vl_despesa) as sum_despesa FROM t_despesa WHERE cd_usuario = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUser);
						
			result = stmt.executeQuery();
	
			if(result.next()) {
				sum = result.getDouble("sum_despesa");
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
	
	/**
	 * Soma todas as despesas de um usuário durante um mês específico
	 * @param idUser O código identificador do usuario
	 * @param mes O mes em questão
	 * @return A soma
	 */
	public Double sumDespesasByUserByMonthByYear(int idUser, String mes, String ano) {
		Double sum = 0.0;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT SUM(vl_despesa) as sum_despesa FROM t_despesa WHERE cd_usuario = ? AND TO_CHAR(dt_hr_despesa, 'MM') = ? AND TO_CHAR(dt_hr_despesa, 'YYYY') = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idUser);
			stmt.setString(2, mes);
			stmt.setString(3, ano);
			
			result = stmt.executeQuery();
	
			if(result.next()) {
				sum = result.getDouble("sum_despesa");
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
