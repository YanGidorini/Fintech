package br.com.fintech.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 * @author Yan Gidorini Silva 
 */
public abstract class AtividadeFinanceira {
	/**
	 * nome de qualquer atividade financeira (receita, despesa, objetivo e investimento)
	 */
	private String nome;
	/**
	 * valor de qualquer atividade financeira (receita, despesa, objetivo e investimento)
	 */
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
	
	/**
	 * Apesar do atributo ser BigDecimal, esse método recebe Double porque assim era o atributo antes. Alterar esse método requeria refatorar muito código. Por isso há uma conversão.
	 * @param valor
	 */
	public void setValor(Double valor) {
		BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
		this.valor = bd;
	}
	
}
