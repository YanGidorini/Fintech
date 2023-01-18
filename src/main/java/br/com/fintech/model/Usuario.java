package br.com.fintech.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
	private int idUsuario;
	private String nmUsuario;
	private String dtNascimento;
	private String email;
	private String senha;
	private String genero;
	private Byte foto;
	
	public Usuario() {}
	
	public Usuario(String email, String senha) {	
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	//Método usado para insert no banco de dados
	public Usuario(String nmUsuario, String dtNascimento, String email, String senha, String genero) {
		this.setNmUsuario(nmUsuario);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.setSenha(senha);
		this.setGenero(genero);
	}
	
	//Método usado no selectAll
	public Usuario(int idUsuario, String nmUsuario, String dtNascimento, String email, String senha, String genero) {
		this.setIdUsuario(idUsuario);
		this.setNmUsuario(nmUsuario);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.senha = senha; //Para não encriptar o hash que vem do banco de dados
		this.setGenero(genero);
	}
	
	public Usuario(int idUsuario, String nmUsuario, String dtNascimento, String email, String genero) {
		this.setIdUsuario(idUsuario);
		this.setNmUsuario(nmUsuario);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.setGenero(genero);
	}
	
	public String encrypt(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		algorithm.update(senha.getBytes("UTF-8"));
		
		BigInteger hash = new BigInteger(1, algorithm.digest());
		
		return hash.toString(16);
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nmUsuario=" + nmUsuario + ", dtNascimento=" + dtNascimento
				+ ", email=" + email + ", senha=" + senha + ", genero=" + genero + "]";
	}

	/*getters and setters*/
	public String getNmUsuario() {
		return nmUsuario;
	}
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento2) {
		this.dtNascimento = dtNascimento2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		try {
			this.senha = encrypt(senha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
