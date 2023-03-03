package io.credable.services;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import io.credable.data.external.customer.CustomerRequest;
import io.credable.data.external.customer.CustomerResponse;

public class CustomerClient extends WebServiceGatewaySupport {

    public CustomerResponse getCustomer(String customerNumber){
        CustomerRequest request = new CustomerRequest();
        request.setCustomerNumber(customerNumber);

        CustomerResponse response = (CustomerResponse) getWebServiceTemplate()
            .marshalSendAndReceive("https://kycapitest.credable.io/service/customer", request);

        return response;    
    }
}