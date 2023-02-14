package io.credable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.services.CustomerService;

@RestController
public class SubscribeController {

    @Autowired
    private CustomerService customerService;

    //SOAP request is invoked when number is submitted inside Post mapping method
    @PostMapping("subscribe")
    public List<String> subscribeCustomer (@RequestBody String customer_number) {

        //inject a soapService to make a SOAP request
        String response = customerService.soapRequest(customer_number);
        return List.of(response);

    }
    
}
