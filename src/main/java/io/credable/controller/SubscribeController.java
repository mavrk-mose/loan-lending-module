package io.credable.controller;

import java.util.Map;

import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.credable.config.CustomerConfig;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;
import io.credable.services.CustomerClient;
import io.credable.services.CustomerService;

@RestController
public class SubscribeController {  
    //initialized
    private final CustomerService service;
    private final CustomerClient client;

    public SubscribeController(CustomerService service, 
                               CustomerClient client) {
        this.service = service;
        this.client = client;
    }

    //SOAP request is invoked when number is submitted inside Post mapping 
    @GetMapping("subscribe/{customer_number}")
    public ResponseEntity<Object> getCustomer (@PathVariable String customer_number) throws JAXBException {
        CustomerModel existingCustomer = service.grabCustomer(customer_number);
        if (existingCustomer != null) {
            return 
                new ResponseEntity<>(Map.of("message", "already subscribed", 
                                            "response", existingCustomer), HttpStatus.OK);
        } else {
            try {
                CustomerResponse response = client.getCustomer(customer_number);
                if (response != null && response.getCustomer() != null) {
                    CustomerModel newCustomer = service.storeCustomer(response);
                } else {
                    
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
        
     
    } 
}     
// CustomerModel fetch = null;
        // //returns response in the shape of CustomerResponse
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomerConfig.class);
        // CustomerClient client = context.getBean(CustomerClient.class);
        // CustomerResponse response = client.getCustomer(customer_number);
        // CustomerService request = new CustomerService();
        // fetch = request.subscribeCustomer(response, customer_number);
        // return fetch;
        
    