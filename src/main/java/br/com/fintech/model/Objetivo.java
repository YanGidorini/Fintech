package br.com.fintech.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que abstrai um objeto financeiro, uma meta a ser alcançada com valor e data definido
 */
public class Objetivo extends AtividadeFinanceira {
	private int idObjetivo;
	private BigDecimal vlAtualObjetivo;
	private Double porcentagem;
	private String dtFimObjetivo;
	/**
	 * Atributo de relacionamento: o objeto Usuario associado
	 */
	private Usuario usuario; //relacionamento
	
	public Objetivo() {
		
	}
	
	public Objetivo(int id, String nome, Double valor, Double vlAtualObjetivo, String dtFimObjetivo, Usuario usuario) {
		this.setIdObjetivo(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	public Objetivo(String nome, Double valor, Double vlAtualObjetivo, String dtFimObjetivo, Usuario usuario) {
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	public Objetivo(int id, String nome, Double valor, Double vlAtualObjetivo, Double porcentagem, String dtFimObjetivo, Usuario usuario) {
		this.setIdObjetivo(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setVlAtualObjetivo(vlAtualObjetivo);
		this.setPorcentagem(porcentagem);
		this.setDtFimObjetivo(dtFimObjetivo);
		this.setUsuario(usuario);
	}
	
	/**
	 * @return uma string com todos ou alguns atributos do objeto
	 */
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

	public BigDecimal getVlAtualObjetivo() {
		return vlAtualObjetivo;
	}
	
	/**
	 * Apesar do atributo ser BigDecimal, esse método recebe Double porque assim era o atributo antes. Alterar esse método requeria refatorar muito código. Por isso há uma conversão.
	 * @param valor
	 */
	public void setVlAtualObjetivo(Double vlAtualObjetivo) {
		BigDecimal bd = new BigDecimal(vlAtualObjetivo).setScale(2, RoundingMode.HALF_UP);
		this.vlAtualObjetivo = bd;
	}
	
	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	
}
