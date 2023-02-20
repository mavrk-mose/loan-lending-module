package io.credable.controller;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.credable.config.CustomerConfig;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.services.CustomerClient;

@RestController
public class SubscribeController {
    
    //SOAP request is invoked when number is submitted inside Post mapping 
    @GetMapping("subscribe/{customer_number}")
    public CustomerResponse subscribeCustomer (@PathVariable String customer_number) throws JAXBException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomerConfig.class);
        CustomerClient client = context.getBean(CustomerClient.class);
        CustomerResponse response = client.getCustomer(customer_number);
        return response;
    }
    
}
