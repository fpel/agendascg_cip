//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:28:55 AM BRST 
//


package br.com.getnet.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Grupo_ASCG026RET_AgendRecsdoComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ASCG026RET_AgendRecsdoComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CNPJBaseCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJBaseCodErr"/>
 *         &lt;element name="CNPJCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJCodErr"/>
 *         &lt;element name="ISPBIFCredr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CNPJBaseCodErr"/>
 *         &lt;element name="AgCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}AgenciaCodErr"/>
 *         &lt;element name="CtCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CtBancariaCodErr"/>
 *         &lt;element name="NomCreddr" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}NomeCodErr"/>
 *         &lt;element name="Grupo_ASCG026_CentrlzRecsdo" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}Grupo_ASCG026_CentrlzRecsdoComplexType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CodErro" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CodErro" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ASCG026RET_AgendRecsdoComplexType", propOrder = {
    "cnpjBaseCreddr",
    "cnpjCreddr",
    "ispbifCredr",
    "agCreddr",
    "ctCreddr",
    "nomCreddr",
    "grupoASCG026CentrlzRecsdo"
})
public class GrupoASCG026RETAgendRecsdoComplexType {

    @XmlElement(name = "CNPJBaseCreddr", required = true)
    protected CNPJBaseCodErr cnpjBaseCreddr;
    @XmlElement(name = "CNPJCreddr", required = true)
    protected CNPJCodErr cnpjCreddr;
    @XmlElement(name = "ISPBIFCredr", required = true)
    protected CNPJBaseCodErr ispbifCredr;
    @XmlElement(name = "AgCreddr", required = true)
    protected AgenciaCodErr agCreddr;
    @XmlElement(name = "CtCreddr", required = true)
    protected CtBancariaCodErr ctCreddr;
    @XmlElement(name = "NomCreddr", required = true)
    protected NomeCodErr nomCreddr;
    @XmlElement(name = "Grupo_ASCG026_CentrlzRecsdo", required = true)
    protected List<GrupoASCG026CentrlzRecsdoComplexType> grupoASCG026CentrlzRecsdo;
    @XmlAttribute(name = "CodErro")
    protected String codErro;

    /**
     * Gets the value of the cnpjBaseCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link CNPJBaseCodErr }
     *     
     */
    public CNPJBaseCodErr getCNPJBaseCreddr() {
        return cnpjBaseCreddr;
    }

    /**
     * Sets the value of the cnpjBaseCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CNPJBaseCodErr }
     *     
     */
    public void setCNPJBaseCreddr(CNPJBaseCodErr value) {
        this.cnpjBaseCreddr = value;
    }

    /**
     * Gets the value of the cnpjCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link CNPJCodErr }
     *     
     */
    public CNPJCodErr getCNPJCreddr() {
        return cnpjCreddr;
    }

    /**
     * Sets the value of the cnpjCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CNPJCodErr }
     *     
     */
    public void setCNPJCreddr(CNPJCodErr value) {
        this.cnpjCreddr = value;
    }

    /**
     * Gets the value of the ispbifCredr property.
     * 
     * @return
     *     possible object is
     *     {@link CNPJBaseCodErr }
     *     
     */
    public CNPJBaseCodErr getISPBIFCredr() {
        return ispbifCredr;
    }

    /**
     * Sets the value of the ispbifCredr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CNPJBaseCodErr }
     *     
     */
    public void setISPBIFCredr(CNPJBaseCodErr value) {
        this.ispbifCredr = value;
    }

    /**
     * Gets the value of the agCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link AgenciaCodErr }
     *     
     */
    public AgenciaCodErr getAgCreddr() {
        return agCreddr;
    }

    /**
     * Sets the value of the agCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgenciaCodErr }
     *     
     */
    public void setAgCreddr(AgenciaCodErr value) {
        this.agCreddr = value;
    }

    /**
     * Gets the value of the ctCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link CtBancariaCodErr }
     *     
     */
    public CtBancariaCodErr getCtCreddr() {
        return ctCreddr;
    }

    /**
     * Sets the value of the ctCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtBancariaCodErr }
     *     
     */
    public void setCtCreddr(CtBancariaCodErr value) {
        this.ctCreddr = value;
    }

    /**
     * Gets the value of the nomCreddr property.
     * 
     * @return
     *     possible object is
     *     {@link NomeCodErr }
     *     
     */
    public NomeCodErr getNomCreddr() {
        return nomCreddr;
    }

    /**
     * Sets the value of the nomCreddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomeCodErr }
     *     
     */
    public void setNomCreddr(NomeCodErr value) {
        this.nomCreddr = value;
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

    /**
     * Gets the value of the codErro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodErro() {
        return codErro;
    }

    /**
     * Sets the value of the codErro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodErro(String value) {
        this.codErro = value;
    }

}