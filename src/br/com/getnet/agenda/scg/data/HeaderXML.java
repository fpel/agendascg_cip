package br.com.getnet.agenda.scg.data;

import java.math.BigInteger;
import java.util.Date;

public class HeaderXML {
	
	private String nomArq;
	private String numCtrlEmis;
	private String numCtrlDestOr;
	private String ispbEmissor;
	private String ispbDestinatario;
	private Date dtHrArq;
	private BigInteger sitCons;
	private Date dtRef;

	public String getNomArq() {
		return nomArq;
	}
	public void setNomArq(String nomArq) {
		this.nomArq = nomArq;
	}
	public String getNumCtrlEmis() {
		return numCtrlEmis;
	}
	public void setNumCtrlEmis(String numCtrlEmis) {
		this.numCtrlEmis = numCtrlEmis;
	}
	public String getNumCtrlDestOr() {
		return numCtrlDestOr;
	}
	public void setNumCtrlDestOr(String numCtrlDestOr) {
		this.numCtrlDestOr = numCtrlDestOr;
	}
	public String getIspbEmissor() {
		return ispbEmissor;
	}
	public void setIspbEmissor(String ispbEmissor) {
		this.ispbEmissor = ispbEmissor;
	}
	public String getIspbDestinatario() {
		return ispbDestinatario;
	}
	public void setIspbDestinatario(String ispbDestinatario) {
		this.ispbDestinatario = ispbDestinatario;
	}
	public Date getDtHrArq() {
		return dtHrArq;
	}
	public void setDtHrArq(Date dtHrArq) {
		this.dtHrArq = dtHrArq;
	}
	public BigInteger getSitCons() {
		return sitCons;
	}
	public void setSitCons(BigInteger sitCons) {
		this.sitCons = sitCons;
	}
	public Date getDtRef() {
		return dtRef;
	}
	public void setDtRef(Date dtRef) {
		this.dtRef = dtRef;
	}
	
	

}
