package br.com.fintech.model;

/** 
 * @author Yan Gidorini Silva - RM96190
*/
public abstract class AtividadeFinanceira {
	private String nome;
	private Double valor;
	
	/*getters and setters*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
