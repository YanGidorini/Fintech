package br.com.fintech.model;

public class Objetivo extends AtividadeFinanceira {
	private int idObjetivo;
	private Double vlAtualObjetivo;
	private Double porcentagem;
	private String dtFimObjetivo;
	private Usuario usuario; //relacionamento
	
	public Objetivo() {
		
	}
	
	public Objetivo(int id, String nome, Double valor, Double vlAtualObjetivo, String dtFimObjetivo, Usuario usuario) {
		if (vlAtualObjetivo > valor) { return; }
		this.setIdObjetivo(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	public Objetivo(String nome, Double valor, Double vlAtualObjetivo, String dtFimObjetivo, Usuario usuario) {
		if (vlAtualObjetivo > valor) { return; }
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	public Objetivo(int id, String nome, Double valor, Double vlAtualObjetivo, Double porcentagem, String dtFimObjetivo, Usuario usuario) {
		if (vlAtualObjetivo > valor) { return; }
		this.setIdObjetivo(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setPorcentagem(porcentagem);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	
	@Override
	public String toString() {
		return "Objetivo [idObjetivo=" + idObjetivo + ", nm=" + this.getNome() + ", valor="+ this.getValor() + ", vlAtualObjetivo=" + vlAtualObjetivo + ", porcentagem="+ this.getPorcentagem() + ", dtFimObjetivo="
				+ dtFimObjetivo + ", usuario=" + usuario + "]";
	}

	/*Getters and setters*/
	public String getDtFimObjetivo() {
		return dtFimObjetivo;
	}

	public void setDtFimObjetivo(String dtFimObjetivo) {
		this.dtFimObjetivo = dtFimObjetivo;
	}

	public Integer getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(Integer idObjetivo) {
		this.idObjetivo = idObjetivo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getVlAtualObjetivo() {
		return vlAtualObjetivo;
	}

	public void setVlAtualObjetivo(Double vlAtualObjetivo) {
		this.vlAtualObjetivo = vlAtualObjetivo;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	
}
