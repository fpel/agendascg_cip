//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:28:55 AM BRST 
//


package br.com.getnet.xml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Grupo_ASCG026RET_AgendActoComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ASCG026RET_AgendActoComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CNPJBaseCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJBase"/>
 *         &lt;element name="CNPJCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJ"/>
 *         &lt;element name="ISPBIFCredr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJBase"/>
 *         &lt;element name="SitRetReq" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}SitRetReq"/>
 *         &lt;element name="Grupo_ASCG026RET_CentrlzActo" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}Grupo_ASCG026RET_CentrlzActoComplexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Grupo_ASCG026_CentrlzRecsdo" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}Grupo_ASCG026_CentrlzRecsdoComplexType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ASCG026RET_AgendActoComplexType", propOrder = {
    "cnpjBaseCreddr",
    "cnpjCreddr",
    "ispbifCredr",
    "sitRetReq",
    "grupoASCG026RETCentrlzActo",
    "grupoASCG026CentrlzRecsdo"
})
public class GrupoASCG026RETAgendActoComplexType {

    @XmlElement(name = "CNPJBaseCreddr", required = true)
    protected String cnpjBaseCreddr;
    @XmlElement(name = "CNPJCreddr", required = true)
    protected String cnpjCreddr;
    @XmlElement(name = "ISPBIFCredr", required = true)
    protected String ispbifCredr;
    @XmlElement(name = "SitRetReq", required = true)
    protected BigInteger sitRetReq;
    @XmlElement(name = "Grupo_ASCG026RET_CentrlzActo")
    protected List<GrupoASCG026RETCentrlzActoComplexType> grupoASCG026RETCentrlzActo;
    @XmlElement(name = "Grupo_ASCG026_CentrlzRecsdo")
    protected List<GrupoASCG026CentrlzRecsdoComplexType> grupoASCG026CentrlzRecsdo;

    /**
     * Gets the value of the cnpjBaseCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJBaseCreddr() {
        return cnpjBaseCreddr;
    }

    /**
     * Sets the value of the cnpjBaseCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJBaseCreddr(String value) {
        this.cnpjBaseCreddr = value;
    }

    /**
     * Gets the value of the cnpjCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJCreddr() {
        return cnpjCreddr;
    }

    /**
     * Sets the value of the cnpjCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJCreddr(String value) {
        this.cnpjCreddr = value;
    }

    /**
     * Gets the value of the ispbifCredr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISPBIFCredr() {
        return ispbifCredr;
    }

    /**
     * Sets the value of the ispbifCredr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISPBIFCredr(String value) {
        this.ispbifCredr = value;
    }

    /**
     * Gets the value of the sitRetReq property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSitRetReq() {
        return sitRetReq;
    }

    /**
     * Sets the value of the sitRetReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSitRetReq(BigInteger value) {
        this.sitRetReq = value;
    }

    /**
     * Gets the value of the grupoASCG026RETCentrlzActo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grupoASCG026RETCentrlzActo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrupoASCG026RETCentrlzActo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GrupoASCG026RETCentrlzActoComplexType }
     * 
     * 
     */
    public List<GrupoASCG026RETCentrlzActoComplexType> getGrupoASCG026RETCentrlzActo() {
        if (grupoASCG026RETCentrlzActo == null) {
            grupoASCG026RETCentrlzActo = new ArrayList<GrupoASCG026RETCentrlzActoComplexType>();
        }
        return this.grupoASCG026RETCentrlzActo;
    }

    /**
     * Gets the value of the grupoASCG026CentrlzRecsdo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grupoASCG026CentrlzRecsdo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGrupoASCG026CentrlzRecsdo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GrupoASCG026CentrlzRecsdoComplexType }
     * 
     * 
     */
    public List<GrupoASCG026CentrlzRecsdoComplexType> getGrupoASCG026CentrlzRecsdo() {
        if (grupoASCG026CentrlzRecsdo == null) {
            grupoASCG026CentrlzRecsdo = new ArrayList<GrupoASCG026CentrlzRecsdoComplexType>();
        }
        return this.grupoASCG026CentrlzRecsdo;
    }

}
