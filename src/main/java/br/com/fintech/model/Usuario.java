package br.com.fintech.model;

public class Usuario {
	private int idUsuario;
	private String nmUsuario;
	private String dtNascimento;
	private String email;
	private String senha;
	private String genero;
	private Byte foto;
	
	public Usuario() {}
	
	public Usuario(String nmUsuario, String dtNascimento, String email, String senha, String genero) {
		this.setNmUsuario(nmUsuario);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.setSenha(senha);
		this.setGenero(genero);
	}
	
	public Usuario(int idUsuario, String nmUsuario, String dtNascimento, String email, String senha, String genero) {
		this.setIdUsuario(idUsuario);
		this.setNmUsuario(nmUsuario);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.setSenha(senha);
		this.setGenero(genero);
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
		this.senha = senha;
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
