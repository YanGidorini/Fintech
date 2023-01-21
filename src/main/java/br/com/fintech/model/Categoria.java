package br.com.fintech.model;

/**
 * Classe que abstrai uma categoria, um enquadramento para uma despesa
 */
public class Categoria {
	/**
	 * id da categoria
	 */
	private int idCategoria;
	/**
	 * nome da categoria
	 */
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
	
	/**
	 * @return uma string com todos ou alguns atributos do objeto
	 */
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
