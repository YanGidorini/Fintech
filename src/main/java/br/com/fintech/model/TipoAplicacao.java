package br.com.fintech.model;

/**
 * Classe que abstrai qual o tipo de investimento realizado
 */
public class TipoAplicacao {
	private int idTipo;
	private String nmTipo;
	
	public TipoAplicacao() {
		
	}
	
	public TipoAplicacao(String nmTipo) {
		this.setNmTipo(nmTipo);
	}

	public TipoAplicacao(int id, String nmTipo) {
		this.setIdTipo(id);
		this.setNmTipo(nmTipo);
		
	}
	
	/**
	 * @return uma string com todos ou alguns atributos do objeto
	 */
	@Override
	public String toString() {
		return "TipoAplicacao [idTipo=" + idTipo + ", nmTipo=" + nmTipo + "]";
	}

	/*getters and setters*/
	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getNmTipo() {
		return nmTipo;
	}

	public void setNmTipo(String nmTipo) {
		this.nmTipo = nmTipo;
	}
	
}
