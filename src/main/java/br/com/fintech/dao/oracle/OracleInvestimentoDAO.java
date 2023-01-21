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
import br.com.fintech.model.Corretora;
import br.com.fintech.model.Investimento;
import br.com.fintech.model.TipoAplicacao;
import br.com.fintech.model.Usuario;

public class OracleInvestimentoDAO implements DefaultDAO {
	/**
	 * Variável que armazena conexão
	 */
	private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		Investimento investimento = (Investimento) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "INSERT INTO t_investimento (cd_investimento, nm_aplicacao, vl_aplicacao, dt_realizacao, dt_vencimento, cd_usuario, cd_corretora, cd_tipo)"
					+ 	" VALUES (sq_investimento.nextval, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, investimento.getNome());
			stmt.setBigDecimal(2, investimento.getValor());
			stmt.setString(3, investimento.getDtRealizacao());
			stmt.setString(4, investimento.getDtVencimento());
			stmt.setInt(5, investimento.getUsuario().getIdUsuario());
			stmt.setInt(6, investimento.getCorretora().getIdCorretora());
			stmt.setInt(7, investimento.getTipo().getIdTipo());
			
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
		Investimento investimento = (Investimento) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "UPDATE t_investimento SET "
						+ "nm_aplicacao = ?, "
						+ "vl_aplicacao = ?, "
						+ "dt_realizacao = TO_DATE(?, 'YYYY-MM-DD'),"
						+ "dt_vencimento = TO_DATE(?, 'YYYY-MM-DD'),"
						+ "cd_corretora = ?,"
						+ "cd_tipo = ? "
						+ "WHERE cd_investimento = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, investimento.getNome());
			stmt.setBigDecimal(2, investimento.getValor());
			stmt.setString(3, investimento.getDtRealizacao());
			stmt.setString(4, investimento.getDtVencimento());
			stmt.setInt(5, investimento.getCorretora().getIdCorretora());
			stmt.setInt(6, investimento.getTipo().getIdTipo());
			stmt.setInt(7, investimento.getIdInvestimento());
			
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
			
			String sql = "DELETE FROM t_investimento WHERE cd_investimento = ?";
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
	public List<Investimento> selectAll() {
		PreparedStatement stmt = null;
		Investimento inv = null;
		ResultSet result = null;
		List<Investimento> invList = new ArrayList<Investimento>();
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_investimento, nm_aplicacao, vl_aplicacao, "
						+ "TO_CHAR(dt_realizacao, 'YYYY-MM-DD') as dt_realizacao, "
						+ "TO_CHAR(dt_vencimento, 'YYYY-MM-DD') as dt_vencimento, "
						+ "cd_usuario, cd_corretora, cd_tipo "
						+ "FROM t_investimento ORDER BY cd_investimento ASC";
			stmt = conn.prepareStatement(sql);
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				int idInv = result.getInt("CD_INVESTIMENTO");
				String nmInv = result.getString("NM_APLICACAO");
				Double valor = result.getDouble("VL_APLICACAO");
				String dtRealizacao = result.getString("dt_realizacao");
				String dtVencimento = result.getString("dt_vencimento");
				
				//pegando o código dos relacionamentos
				int idCorretora = result.getInt("CD_CORRETORA");
				int idTipo = result.getInt("CD_TIPO");
				int idUser = result.getInt("CD_USUARIO");
				
				//instanciado objetos a partir do código
				OracleCorretoraDAO corrDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
				Corretora corretora = corrDao.selectById(idCorretora);
				
				OracleTipoAplicacaoDAO tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();
				TipoAplicacao tipo = tipoDao.selectById(idTipo);
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				//Finalmente instanciando o objeto Investimento
				inv = new Investimento(idInv, nmInv, valor, dtRealizacao, dtVencimento, corretora, tipo, user);
				
				invList.add(inv);
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
		
		return invList;
	}

	@Override
	public Investimento selectById(int id) {
		PreparedStatement stmt = null;
		Investimento inv = null;
		ResultSet result = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_investimento, nm_aplicacao, vl_aplicacao, "
						+ "TO_CHAR(dt_realizacao, 'YYYY-MM-DD') as dt_realizacao, "
						+ "TO_CHAR(dt_vencimento, 'YYYY-MM-DD') as dt_vencimento, "
						+ "cd_usuario, cd_corretora, cd_tipo "
						+ "FROM t_investimento WHERE cd_investimento = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				int idInv = result.getInt("CD_INVESTIMENTO");
				String nmInv = result.getString("NM_APLICACAO");
				Double valor = result.getDouble("VL_APLICACAO");
				String dtRealizacao = result.getString("dt_realizacao");
				String dtVencimento = result.getString("dt_vencimento");
				
				//pegando o código dos relacionamentos
				int idCorretora = result.getInt("CD_CORRETORA");
				int idTipo = result.getInt("CD_TIPO");
				int idUser = result.getInt("CD_USUARIO");
				
				//instanciado objetos a partir do código
				OracleCorretoraDAO corrDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
				Corretora corretora = corrDao.selectById(idCorretora);
				
				OracleTipoAplicacaoDAO tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();
				TipoAplicacao tipo = tipoDao.selectById(idTipo);
				
				OracleUsuarioDAO userDao = (OracleUsuarioDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getUsuarioDAO();
				Usuario user = userDao.selectById(idUser);
				
				//Finalmente instanciando o objeto Investimento
				inv = new Investimento(idInv, nmInv, valor, dtRealizacao, dtVencimento, corretora, tipo, user);
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
		
		return inv;
	}
	
	/**
	 * Seleciona todos os investimentos de um usuário
	 * @param user O objeto usuário
	 * @return Lista com todos os investimentos de um usuario específico com as datas formatadas para visualização
	 */
	public List<Investimento> selectAllByUser(Usuario user) {
		PreparedStatement stmt = null;
		List<Investimento> invList = new ArrayList<Investimento>();
		ResultSet result = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_investimento, nm_aplicacao,"
						+ "vl_aplicacao, "
						+ "TO_CHAR(dt_realizacao, 'YYYY-MM-DD') as dt_realizacao, "
						+ "TO_CHAR(dt_vencimento, 'YYYY-MM-DD') as dt_vencimento, "
						+ "TO_CHAR(dt_realizacao, 'DD/MM/YYYY') as dt_realizacaoToView, "
						+ "TO_CHAR(dt_vencimento, 'DD/MM/YYYY') as dt_vencimentoToView, "
						+ "cd_usuario, cd_corretora, cd_tipo "
						+ "FROM t_investimento WHERE cd_usuario = ? ORDER BY dt_realizacao ASC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getIdUsuario());
			
			result = stmt.executeQuery();
			
			while(result.next()) {
				int idInv = result.getInt("CD_INVESTIMENTO");
				String nmInv = result.getString("NM_APLICACAO");
				Double valor = result.getDouble("VL_APLICACAO");
				String dtRealizacao = result.getString("dt_realizacao");
				String dtVencimento = result.getString("dt_vencimento");
				String dtRealizacaoToView = result.getString("dt_realizacaoToView");
				String dtVencimentoToView = result.getString("dt_vencimentoToView");
				
				//pegando o código dos relacionamentos
				int idCorretora = result.getInt("CD_CORRETORA");
				int idTipo = result.getInt("CD_TIPO");
				
				//instanciado objetos a partir do código
				OracleCorretoraDAO corrDao = (OracleCorretoraDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getCorretoraDAO();
				Corretora corretora = corrDao.selectById(idCorretora);
				
				OracleTipoAplicacaoDAO tipoDao = (OracleTipoAplicacaoDAO) DAOFactory.getDAOFactory(DAOFactory.ORACLE).getTipoAplicacaoDAO();
				TipoAplicacao tipo = tipoDao.selectById(idTipo);

				//Finalmente instanciando o objeto Investimento
				Investimento inv = new Investimento(idInv, nmInv, valor, dtRealizacao, dtVencimento, corretora, tipo, user);
				inv.setDtRealizacaoToView(dtRealizacaoToView);
				inv.setDtVencimentoToView(dtVencimentoToView);
				
				invList.add(inv);
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
		
		return invList;
	}
	
	/**
	 * Soma todos os investimentos do usuário
	 * @param user Objeto usuário
	 * @return o valor soma dos investimentos
	 */
	public Double sumAllInvestimentosByUser(Usuario user) {
		Double sum = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT SUM(vl_aplicacao) as sum_investimentos FROM t_investimento WHERE cd_usuario = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getIdUsuario());
						
			result = stmt.executeQuery();
	
			if(result.next()) {
				sum = result.getDouble("sum_investimentos");
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
	}
	
}
