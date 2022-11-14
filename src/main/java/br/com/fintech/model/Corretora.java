package br.com.fintech.model;

public class Corretora {
	private int idCorretora;
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
