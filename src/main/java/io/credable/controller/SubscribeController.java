package io.credable.controller;

//import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.credable.config.CustomerConfig;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;
import io.credable.data.repository.CustomerDAO;
import io.credable.services.CustomerClient;
import io.credable.services.CustomerService;
import jakarta.xml.ws.soap.SOAPFaultException;

@RestController
public class SubscribeController {  
    
    //initialized
    @Autowired
    private CustomerDAO customerDAO;
    private final CustomerService service;

    public SubscribeController(CustomerService service) {
        this.service = service;
    }

    //SOAP request is invoked when number is submitted inside Post mapping 
    @GetMapping("subscribe/{customer_number}")
    public ResponseEntity<Object> getCustomer (@PathVariable String customer_number) throws JAXBException {
        Optional<CustomerModel> customerOpt = customerDAO.findByCustomerNumber(customer_number);
        //if customer is in database print already subscribed
        if (customerOpt.isPresent()) {
            return new ResponseEntity<>(Map.of("Message", "Already subscribed"), HttpStatus.OK);
        } else {
            //if customer is not in database fetch data
            try {
                //returns response in the shape of CustomerResponse
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomerConfig.class);
                CustomerClient client = context.getBean(CustomerClient.class);
                CustomerResponse response = client.getCustomer(customer_number);
                
                if (response != null && response.getCustomer() != null) {
                    CustomerModel newCustomer = service.storeCustomer(response);
                    return 
                        new ResponseEntity<>(Map.of("Message", "Successfully subscribed", 
                                                    "response", newCustomer), HttpStatus.OK);
                } else {
                    return 
                        new ResponseEntity<>("Customer Number doesn't exist", HttpStatus.NOT_FOUND);
                }
            } catch (SOAPFaultException e) {
                //handle exception
                return 
                    new ResponseEntity<>("Error retrieving customer data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } 
        }
              
    } 
}     
       
        
        