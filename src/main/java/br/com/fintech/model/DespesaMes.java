package br.com.fintech.model;

import java.util.List;

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
