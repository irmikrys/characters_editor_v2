
package org.soa.soap.editor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ElementNotFoundException", targetNamespace = "http://soa.org/soap/editor")
public class ElementNotFoundException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ElementNotFoundException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ElementNotFoundException_Exception(String message, ElementNotFoundException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ElementNotFoundException_Exception(String message, ElementNotFoundException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.soa.soap.editor.ElementNotFoundException
     */
    public ElementNotFoundException getFaultInfo() {
        return faultInfo;
    }

}