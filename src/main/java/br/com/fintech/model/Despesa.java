package br.com.fintech.model;

public class Despesa extends AtividadeFinanceira {
	private int idDespesa;
	private String dtDespesa;
	private String dtExtenso;
	private String ano;
	private String mes;
	private String hora;
	private Categoria categoria; //relacionamento
	private Usuario usuario; //relacionamento
	
	public Despesa() {
		
	}
	
	public Despesa(String nome, Double valor, String ano, String mes, String dtExtenso, String hora, Usuario user, Categoria categoria) {
		this.setNome(nome);
		this.setValor(valor);
		this.setAno(ano);
		this.setMes(mes);
		this.setDtExtenso(dtExtenso);
		this.setHora(hora);
		this.setUsuario(user);
		this.setCategoria(categoria);
	}
	//usado para insert
	public Despesa(String nome, Double valor, String dtDespesa, Categoria categoria, Usuario usuario) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDtDespesa(dtDespesa);
		this.setCategoria(categoria);
		this.setUsuario(usuario);
	}
	
	//usado para selectById
	public Despesa(int id, String nome, Double valor, String dtDespesa, Categoria categoria, Usuario usuario) {
		this.setIdDespesa(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtDespesa(dtDespesa);
		this.setCategoria(categoria);
		this.setUsuario(usuario);
	}
	
	//
	public Despesa(int id, String nome, Double valor, String dtExtenso, String hr,  Categoria categoria, Usuario usuario) {
		this.setIdDespesa(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtExtenso(dtExtenso);
		this.setHora(hr);
		this.setCategoria(categoria);
		this.setUsuario(usuario);
	}
	
	
	@Override
	public String toString() {
		return "Despesa [idDespesa=" + idDespesa + ", nm=" + this.getNome() + ", valor=" + this.getValor() + ", dtHrDespesa=" + dtDespesa + ", dtExtenso=" + dtExtenso
				+ ", hora=" + hora + ", categoria=" + categoria + ", usuario=" + usuario + "]";
	}

	/*Getters and setters*/
	public String getDtDespesa() {
		return dtDespesa;
	}

	public void setDtDespesa(String dtDespesa) {
		this.dtDespesa = dtDespesa;
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDtExtenso() {
		return dtExtenso;
	}

	public void setDtExtenso(String dtExtenso) {
		this.dtExtenso = dtExtenso;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
}

