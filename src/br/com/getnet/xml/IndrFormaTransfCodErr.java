//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:28:55 AM BRST 
//


package br.com.getnet.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;cat:InfTipo xmlns:cat="http://www.cip-bancos.org.br/catalogomsg" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;cat:DescricaoTipo&gt;
 *             Indicador da Forma de Pagamento
 *           &lt;/cat:DescricaoTipo&gt;
 *         &lt;/cat:InfTipo&gt;
 * </pre>
 * 
 *       
 * 
 * <p>Java class for IndrFormaTransfCodErr complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndrFormaTransfCodErr">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.cip-bancos.org.br/ARQ/ASCG026.xsd>IndrFormaTransf">
 *       &lt;attribute name="CodErro" type="{http://www.cip-bancos.org.br/ARQ/ASCG026.xsd}CodErro" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndrFormaTransfCodErr", propOrder = {
    "value"
})
public class IndrFormaTransfCodErr {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "CodErro")
    protected String codErro;

    /**
     * 
     *         
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;cat:InfTipo xmlns:cat="http://www.cip-bancos.org.br/catalogomsg" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;cat:DescricaoTipo&gt;Indicador da Forma de Pagamento&lt;/cat:DescricaoTipo&gt;
     *         &lt;/cat:InfTipo&gt;
     * </pre>
     * 
     *       
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
