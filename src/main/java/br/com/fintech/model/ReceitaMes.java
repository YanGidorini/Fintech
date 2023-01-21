package br.com.fintech.model;

import java.util.List;

/**
 * Classe que armazena o ano, o mes, uma lista de objetos Receitas e o total do mes, ou seja, essa classe serve para sumarizar por mes as despesas e facilitar na construção da jsp
 */
public class ReceitaMes {

	  private String year;
	  private String mes;
	  private List<Receita> receitas;
	  private Double total;

	  public ReceitaMes(String year, String mes, List<Receita> receitas, Double total) {
	    this.year = year;
	    this.mes = mes;
	    this.receitas = receitas;
	    this.total = total;
	  }

	  public String getYear() {
	    return year;
	  }

	  public String getMes() {
	    return mes;
	  }

	  public List<Receita> getReceitas() {
	    return receitas;
	  }

	public Double getTotal() {
		return total;
	}

}
