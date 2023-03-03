package io.credable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.credable.services.SubscribeService;

@RestController
public class SubscribeController {  
    @Autowired
    private SubscribeService service;
    
    //SOAP request is invoked when number is submitted inside Post mapping 
    @GetMapping("subscribe/{customer_number}")
    public ResponseEntity<Object> getCustomer (@PathVariable String customerNumber) {
        ResponseEntity<Object> response = service.fetchData(customerNumber);
        return response;
    } 
}     
       
        
        