package br.com.fintech.model;



public class Investimento extends AtividadeFinanceira {
	private int idInvestimento;
	private String dtRealizacao;
	private String dtVencimento;
	private String dtRealizacaoToView;
	private String dtVencimentoToView;
	private Usuario usuario; //relacionamento
	private Corretora corretora; //relacionamento
	private TipoAplicacao tipo; //relacionamento
	
	public Investimento(){}
	
	//Sem data de vencimento
	public Investimento(String nome, Double valor, String dtRealizacao, Corretora corretora, TipoAplicacao tipo, Usuario usuario) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDtRealizacao(dtRealizacao);
		this.setCorretora(corretora);
		this.setTipo(tipo);
		this.setUsuario(usuario);
	}
	
	//Com data de vencimento
	public Investimento(String nome, Double valor, String dtRealizacao, String dtVencimento, Corretora corretora, TipoAplicacao tipo, Usuario usuario) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDtRealizacao(dtRealizacao);
		this.setDtVencimento(dtVencimento);
		this.setCorretora(corretora);
		this.setTipo(tipo);
		this.setUsuario(usuario);
	}
	
	//Sem data de vencimento
	public Investimento(int id, String nome, Double valor, String dtRealizacao, Corretora corretora, TipoAplicacao tipo, Usuario usuario) {
		this.setIdInvestimento(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtRealizacao(dtRealizacao);
		this.setCorretora(corretora);
		this.setTipo(tipo);
		this.setUsuario(usuario);
	}
	
	// Com data de vencimento
	public Investimento(int id, String nome, Double valor, String dtRealizacao, String dtVencimento, Corretora corretora, TipoAplicacao tipo, Usuario usuario) {
		this.setIdInvestimento(id);
		this.setNome(nome);
		this.setValor(valor);
		this.setDtRealizacao(dtRealizacao);
		this.setDtVencimento(dtVencimento);
		this.setCorretora(corretora);
		this.setTipo(tipo);
		this.setUsuario(usuario);
	}
		
	@Override
	public String toString() {
		return "Investimento [idInvestimento=" + idInvestimento + ", nome=" + this.getNome() + ", valor=" + this.getValor() + ", dtRealizacao=" + dtRealizacao + ", dtVencimento=" + dtVencimento + 
				", usuario=" + usuario + ", corretora=" + corretora + ", tipo=" + tipo + "]";
	}

	/*Getters and setters*/
	public String getDtRealizacao() {
		return dtRealizacao;
	}

	public void setDtRealizacao(String dtRealizacao) {
		this.dtRealizacao = dtRealizacao;
	}

	public String getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Integer getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(Integer idInvestimento) {
		this.idInvestimento = idInvestimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}

	public TipoAplicacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoAplicacao tipo) {
		this.tipo = tipo;
	}

	public String getDtRealizacaoToView() {
		return dtRealizacaoToView;
	}

	public void setDtRealizacaoToView(String dtRealizacaoToView) {
		this.dtRealizacaoToView = dtRealizacaoToView;
	}

	public String getDtVencimentoToView() {
		return dtVencimentoToView;
	}

	public void setDtVencimentoToView(String dtVencimentoToView) {
		this.dtVencimentoToView = dtVencimentoToView;
	}
}
