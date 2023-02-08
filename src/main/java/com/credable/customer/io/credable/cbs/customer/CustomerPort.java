
package com.credable.customer.io.credable.cbs.customer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerPort", targetNamespace = "http://credable.io/cbs/customer")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerPort {


    /**
     * 
     * @param customerRequest
     * @return
     *     returns io.credable.cbs.customer.CustomerResponse
     */
    @WebMethod(operationName = "Customer")
    @WebResult(name = "CustomerResponse", targetNamespace = "http://credable.io/cbs/customer", partName = "CustomerResponse")
    public CustomerResponse customer(
        @WebParam(name = "CustomerRequest", targetNamespace = "http://credable.io/cbs/customer", partName = "CustomerRequest")
        CustomerRequest customerRequest);

}
