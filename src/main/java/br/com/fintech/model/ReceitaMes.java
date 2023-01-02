package br.com.fintech.model;

import java.util.List;

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
