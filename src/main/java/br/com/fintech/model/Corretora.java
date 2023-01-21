package br.com.fintech.model;

/**
 * Classe que abstrai uma corretora ou banco. Essa classe se associa com Investimento.java
 */
public class Corretora {
	/**
	 * id da corretora
	 */
	private int idCorretora;
	/**
	 * nome da corretora
	 */
	private String nmCorretora;
	
	public Corretora() {
		
	}
	
	public Corretora(String nmCorretora) {
		this.setNmCorretora(nmCorretora);
	}
	
	public Corretora(int id, String nmCorretora) {
		this.setIdCorretora(id);
		this.setNmCorretora(nmCorretora);
	}
	
	/**
	 * @return uma string com todos ou alguns atributos do objeto
	 */
	@Override
	public String toString() {
		return "Corretora [idCorretora=" + idCorretora + ", nmCorretora=" + nmCorretora + "]";
	}

	/*getters and setters*/
	public Integer getIdCorretora() {
		return idCorretora;
	}

	public void setIdCorretora(Integer idCorretora) {
		this.idCorretora = idCorretora;
	}

	public String getNmCorretora() {
		return nmCorretora;
	}

	public void setNmCorretora(String nmCorretora) {
		this.nmCorretora = nmCorretora;
	}
	
	
}
