package br.com.getnet.agenda.scg.data;

public class Registro {
	private String banco;
	private String codigo_ec;
	private String centralizador;
	private String cnpj_centralizador;
	private String agencia;
	private String conta;
	private String valor;
	private String data_pagamento;
	private String moeda;
	private String cnpj_ec;
	private String produto;
	private String tipoPessoaEc;
	private String tipoPessoaCent;
	private long sequencia;
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCodigo_ec() {
		return codigo_ec;
	}
	public void setCodigo_ec(String codigo_ec) {
		this.codigo_ec = codigo_ec;
	}
	public String getCentralizador() {
		return centralizador;
	}
	public void setCentralizador(String centralizador) {
		this.centralizador = centralizador;
	}
	public String getCnpj_centralizador() {
		return cnpj_centralizador;
	}
	public void setCnpj_centralizador(String cnpj_centralizador) {
		this.cnpj_centralizador = cnpj_centralizador;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getData_pagamento() {
		return data_pagamento;
	}
	public void setData_pagamento(String data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getCnpj_ec() {
		return cnpj_ec;
	}
	public void setCnpj_ec(String cnpj_ec) {
		this.cnpj_ec = cnpj_ec;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getTipoPessoaEc() {
		return tipoPessoaEc;
	}
	public void setTipoPessoaEc(String tipoPessoaEc) {
		this.tipoPessoaEc = tipoPessoaEc;
	}
	public String getTipoPessoaCent() {
		return tipoPessoaCent;
	}
	public void setTipoPessoaCent(String tipoPessoaCent) {
		this.tipoPessoaCent = tipoPessoaCent;
	}
	public long getSequencia() {
		return sequencia;
	}
	public void setSequencia(long sequencia) {
		this.sequencia = sequencia;
	}
	
}
