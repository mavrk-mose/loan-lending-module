package io.credable.services;

import io.credable.config.CustomerConfig;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;
import io.credable.data.repository.CustomerDAO;
import io.micrometer.core.annotation.Timed;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.Map;
import java.util.Optional;

@Service
public class SubscribeService {
    //initialized
    private final CustomerDAO customerDAO;
    
    private final CustomerService service;

    private static final String MESSAGE = "Message";
    public SubscribeService(CustomerService service, CustomerDAO customerDAO) {
        this.service = service;
        this.customerDAO = customerDAO;
    }

    @Timed(description = "Time spent to fetching customer data", histogram = true)
    public ResponseEntity<Object> fetchData (String customerNumber){

        Optional<CustomerModel> customerOpt = customerDAO.findByCustomerNumber(customerNumber);
        //if customer is in database print already subscribed
        if (customerOpt.isPresent()) {
            return new ResponseEntity<>(Map.of(MESSAGE, "Already subscribed",
                                              "Customer Data", customerDAO.findByCustomerNumber(customerNumber)), HttpStatus.OK);
        } else {
            //if customer is not in database fetch data
            try {
                //returns response in the shape of CustomerResponse
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomerConfig.class);
                CustomerClient client = context.getBean(CustomerClient.class);
                CustomerResponse response = client.getCustomer(customerNumber);
                
                if (response != null && response.getCustomer() != null) {
                    CustomerModel newCustomer = service.storeCustomer(response);
                    return 
                        new ResponseEntity<>(Map.of(MESSAGE, "Successfully subscribed", 
                                                    "response", newCustomer), HttpStatus.OK);
                } else {
                    return 
                        new ResponseEntity<>(Map.of(MESSAGE, "Customer doesn't exist"), HttpStatus.NOT_FOUND);
                }
            } catch (SOAPFaultException e) {
                //handle exception
                return 
                    new ResponseEntity<>(Map.of("Error retrieving customer data", e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
            } 
        }
    }
}
