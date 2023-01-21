package br.com.fintech.model;

import java.util.List;

/**
 * Classe que armazena o ano, o mes, uma lista de objetos Despesas e o total do mes, ou seja, essa classe serve para sumarizar por mes as despesas e facilitar na construção da jsp
 */
public class DespesaMes {

	  private String year;
	  private String mes;
	  private List<Despesa> despesas;
	  private Double total;

	  public DespesaMes(String year, String mes, List<Despesa> despesas, Double total) {
	    this.year = year;
	    this.mes = mes;
	    this.despesas = despesas;
	    this.total = total;
	  }

	  public String getYear() {
	    return year;
	  }

	  public String getMes() {
	    return mes;
	  }

	  public List<Despesa> getDespesas() {
	    return despesas;
	  }

	public Double getTotal() {
		return total;
	}
}
