package br.com.getnet.agenda.scg.io;

public class DomicilioBancario {
	private long codigoBacen;
	private String agencia;
	private String dvAgencia;
	private String conta;
	private String dvConta;
	private String cnpj;
	
	public DomicilioBancario(long codigoBacen, String agencia,
			String dvAgencia, String conta, String dvConta) {
		super();
		this.codigoBacen = codigoBacen;
		this.agencia = agencia;
		this.dvAgencia = dvAgencia;
		this.conta = conta;
		this.dvConta = dvConta;
	}
	public DomicilioBancario(long codigoBacen, String agencia,
			String dvAgencia, String conta, String dvConta, String cnpj) {
		super();
		this.codigoBacen = codigoBacen;
		this.agencia = agencia;
		this.dvAgencia = dvAgencia;
		this.conta = conta;
		this.dvConta = dvConta;
		this.cnpj = cnpj;
	}
	public long getCodigoBacen() {
		return codigoBacen;
	}
	public void setCodigoBacen(long codigoBacen) {
		this.codigoBacen = codigoBacen;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getDvAgencia() {
		return dvAgencia;
	}
	public void setDvAgencia(String dvAgencia) {
		this.dvAgencia = dvAgencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getDvConta() {
		return dvConta;
	}
	public void setDvConta(String dvConta) {
		this.dvConta = dvConta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigoBacen ^ (codigoBacen >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomicilioBancario other = (DomicilioBancario) obj;
		if (codigoBacen != other.codigoBacen)
			return false;
		return true;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
}
