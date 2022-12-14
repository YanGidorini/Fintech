package br.com.fintech.model;

public class Receita extends AtividadeFinanceira {
	private int idReceita;
	private String dtReceita;
	private String dtExtenso;
	private String hora;
	private Usuario usuario; //relacionamento
	
	public Receita() {}
	
	public Receita(String nome, Double valor, String dtReceita, Usuario usuario) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDtReceita(dtReceita);
		this.setUsuario(usuario);
	}
	
	public Receita(int id, String nome, Double valor, String dtReceita, Usuario usuario) {
		this.setIdReceita(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtReceita(dtReceita);
		this.setUsuario(usuario);
	}
	
	public Receita(int id, String nome, Double valor, String dtExtenso, String hr, Usuario usuario) {
		this.setIdReceita(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtExtenso(dtExtenso);
		this.setHora(hr);
		this.setUsuario(usuario);
	}
	
	@Override
	public String toString() {
		return "Receita [idReceita=" + idReceita + ", dtReceita=" + dtReceita + ", dtExtenso=" + dtExtenso + ", hora="
				+ hora + ", usuario=" + usuario + ", nm=" + getNome() + ", valor=" + getValor() + "]";
	}

	/*Getters and setters*/
	public int getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
	}

	public String getDtReceita() {
		return dtReceita;
	}

	public void setDtReceita(String dtReceita) {
		this.dtReceita = dtReceita;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDtExtenso() {
		return dtExtenso;
	}

	public void setDtExtenso(String dtExtenso) {
		this.dtExtenso = dtExtenso;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
