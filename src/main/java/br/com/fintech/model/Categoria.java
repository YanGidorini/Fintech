package br.com.fintech.model;

public class Categoria {
	private int idCategoria;
	private String nmCategoria;
	
	public Categoria() {
		
	}
	
	public Categoria(String nmCategoria) {
		this.setNmCategoria(nmCategoria);
	}
	
	public Categoria(int idCategoria, String nmCategoria) {
		this.setNmCategoria(nmCategoria);
		this.setIdCategoria(idCategoria);
	}
	
	
	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nmCategoria=" + nmCategoria + "]";
	}

	/*Getters and setters*/
	public String getNmCategoria() {
		return nmCategoria;
	}
	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}	
}
