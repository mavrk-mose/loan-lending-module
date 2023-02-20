package io.credable.services;

// import java.util.Map;

// import javax.xml.ws.soap.SOAPFaultException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

// import io.credable.data.repository.customerDAO;
// import io.credable.data.external.customer.CustomerResponse;
@Service
public class SubscribeService {

    // public ResponseEntity<Object> SubscribeCustomer(String customer_number, Object newCustomerResponse) {

    //     CustomerService customerService = new CustomerService();
    //     CustomerClient customerClient = new CustomerClient();
    //     customerDAO existingCustomer = customerService.getCustomer(customer_number);

    //     if (existingCustomer!=null) {
    //         //if customer number is found in database respond with
    //         return 
    //             new ResponseEntity<>(Map.of("message", 
    //                                         "customer already subscribed", 
    //                                         "response", existingCustomer), HttpStatus.OK);
    //     } else {
    //         try {
    //             //if customer not found in database, invoke SOAP request
    //             CustomerResponse newCustomer = customerClient.getCustomer(customer_number);

    //             if (newCustomer != null && newCustomer.getCustomer() != null) {
    //                 //if number is found in Core CBS, save to local database
    //                // CustomerResponse newCustomerData = customerDAO.save(newCustomerData, customer_number);
    //                 return 
    //                     new ResponseEntity<>(Map.of(
    //                                                 ), HttpStatus.OK);
    //             }else{
    //                 return 
    //                     new ResponseEntity<>("Customer is not subscribed",HttpStatus.NOT_FOUND);  
    //             }
    //         }
    //         catch (SOAPFaultException ex) {
    //             // meant to handle the SOAP request exception
    //                 return 
    //                     new ResponseEntity<>("Error retrieving customer data" 
    //                                         +ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);       
    //         }
    //     }
    // }

}