package io.credable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.credable.services.StatusService;

@RestController
public class StatusController {
    
    @Autowired
    private final StatusService service;
    
    public StatusController(StatusService service) {
        this.service = service;
    }

    @GetMapping("/check-status/{customerNumber}")
    public String getLoanStatus (@PathVariable String customerNumber) {
        return service.checkLoanStatus(customerNumber);
    }


}
