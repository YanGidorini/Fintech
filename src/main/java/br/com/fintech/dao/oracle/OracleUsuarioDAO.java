package br.com.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.dao.DefaultDAO;
import br.com.fintech.database.DBConnectionManager;
import br.com.fintech.model.Usuario;

/**
 * Classe que implementa a interface UsuarioDAO para o banco de dados Oracle
 * @author Yan Gidorini Silva
 */
public class OracleUsuarioDAO implements DefaultDAO {
	
	private Connection conn = null;
	
	@Override
	public void insert(Object obj) {
		Usuario user =  (Usuario) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "INSERT INTO t_usuario (cd_usuario, nm_usuario, dt_nascimento, ds_email, ds_senha, genero) VALUES (sq_usuario.nextval, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getNmUsuario());
			stmt.setString(2, user.getDtNascimento());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getSenha());
			stmt.setString(5, user.getGenero());
			
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
	/**
	 * Este método não atualiza a senha
	 */
	public void update(Object obj) {
		Usuario user =  (Usuario) obj;
		PreparedStatement stmt = null;
		
		try {
			this.conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "UPDATE t_usuario SET nm_usuario = ?, dt_nascimento = TO_DATE(?, 'YYYY-MM-DD'), ds_email = ?, genero = ?"
						+ "WHERE cd_usuario = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getNmUsuario());
			stmt.setString(2, user.getDtNascimento());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getGenero());
			stmt.setInt(5, user.getIdUsuario());
			
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

			stmt = conn.prepareStatement("DELETE FROM t_usuario WHERE cd_usuario = ?");
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
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
	public List<Usuario> selectAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Usuario> userList = new ArrayList<Usuario>();
		
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT cd_usuario, nm_usuario, TO_CHAR(dt_nascimento, 'YYYY-MM-DD') as dt_nascimento, ds_email, ds_senha, genero "
						+ "FROM t_usuario ORDER BY cd_usuario ASC";
			stmt = conn.prepareStatement(sql);

			result = stmt.executeQuery();
			
			while(result.next()) {
				int id = result.getInt("CD_USUARIO");
				String nm = result.getString("NM_USUARIO");
				String dtNascimento = result.getString("DT_NASCIMENTO");
				String email = result.getString("DS_EMAIL");
				String senha = result.getString("DS_SENHA");
				String genero = result.getString("GENERO");
				Usuario user = new Usuario (id, nm, dtNascimento, email, senha, genero);
				
				userList.add(user);
			}
			
		} catch (SQLException e) {
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
		return userList;
	}

	@Override
	public Usuario selectById(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		Usuario user = null;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();

			String sql = "SELECT cd_usuario, nm_usuario, TO_CHAR(dt_nascimento, 'YYYY-MM-DD') as dt_nascimento, ds_email, genero "
						+ "FROM t_usuario WHERE cd_usuario = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {
				String nm = result.getString("NM_USUARIO");
				String dtNascimento = result.getString("DT_NASCIMENTO");
				String email = result.getString("DS_EMAIL");
				String genero = result.getString("GENERO");
				user = new Usuario (id, nm, dtNascimento, email, genero);
			}
			
		} catch (SQLException e) {
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
		return user;
	}
	
	/**
	 * Atualiza a senha de um usuário
	 * @param user O objeto do tipo usuario
	 * @param currentPswd A atual senha digitada
	 * @param newPswd A nova senha digitada
	 * @param newPswdConfirmed A confirmação da nova senha digitada
	 * @throws Exception 
	 */
	public void updatePswd(Usuario user, String currentPswd, String newPswd, String confirmedNewPswd) throws Exception {
		PreparedStatement stmt = null;
		String currentPswdInDB = this.selectPswd(user);
		currentPswd = user.encrypt(currentPswd);
				
		//verifica se a senha atual digitada é igual a senha cadastrada
		if( currentPswdInDB.equals(currentPswd) ) {
			//verifica se a nova senha é igual a sua confirmação
			if ( newPswd.equals(confirmedNewPswd) ) {
				user.setSenha(confirmedNewPswd);
				
				try {
					conn = DBConnectionManager.getInstance().getConn();
					
					String sql = "UPDATE t_usuario SET ds_senha = ? WHERE cd_usuario  = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, user.getSenha());
					stmt.setInt(2, user.getIdUsuario());
					
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
			} else {
				throw new Exception("Novas senhas não correspondem");				
			}
		} else {
			throw new Exception("Senha atual incorreta");
		}
		
	}
	
	private String selectPswd(Usuario user) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String pswd = null;
		try {
			conn = DBConnectionManager.getInstance().getConn();
			
			String sql = "SELECT ds_senha FROM t_usuario WHERE cd_usuario = ?";
			stmt = conn.prepareStatement(sql);		
			stmt.setInt(1, user.getIdUsuario());
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				pswd = result.getString("ds_senha");
			}
			
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
		return pswd;
	}

	public int authenticate(Usuario user) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		int id = 0;
		
		try {
			conn = DBConnectionManager.getInstance().getConn();

			String sql = "SELECT cd_usuario FROM t_usuario WHERE ds_email = ? AND ds_senha = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getSenha());
			
			result = stmt.executeQuery();
			
			if(result.next()) {
				id = result.getInt("CD_USUARIO");
			}
			
		} catch (SQLException e) {
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
		
		return id;
	}
}
