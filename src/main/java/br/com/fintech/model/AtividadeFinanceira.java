package br.com.fintech.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 * @author Yan Gidorini Silva - RM96190
*/
public abstract class AtividadeFinanceira {
	private String nome;
	private BigDecimal valor;
	
	/*getters and setters*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
		this.valor = bd;
	}
	
}
