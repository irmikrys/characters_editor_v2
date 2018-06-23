
package org.soa.soap.editor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elementDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="elementDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fortune" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idElement" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="power" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="property" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elementDTO", propOrder = {
    "fortune",
    "idElement",
    "name",
    "power",
    "property"
})
public class ElementDTO {

    protected int fortune;
    protected int idElement;
    protected String name;
    protected int power;
    protected int property;

    /**
     * Gets the value of the fortune property.
     * 
     */
    public int getFortune() {
        return fortune;
    }

    /**
     * Sets the value of the fortune property.
     * 
     */
    public void setFortune(int value) {
        this.fortune = value;
    }

    /**
     * Gets the value of the idElement property.
     * 
     */
    public int getIdElement() {
        return idElement;
    }

    /**
     * Sets the value of the idElement property.
     * 
     */
    public void setIdElement(int value) {
        this.idElement = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the power property.
     * 
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the value of the power property.
     * 
     */
    public void setPower(int value) {
        this.power = value;
    }

    /**
     * Gets the value of the property property.
     * 
     */
    public int getProperty() {
        return property;
    }

    /**
     * Sets the value of the property property.
     * 
     */
    public void setProperty(int value) {
        this.property = value;
    }

}
