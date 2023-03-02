package io.credable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;
import io.credable.services.LoanService;

@RestController
public class LoanController {

    private final ScoringClient client;

    private final LoanService service;
    
    public LoanController(ScoringClient client, LoanService service) {
        this.client = client;
        this.service = service;
    }

    @GetMapping("query-score/{customerNumber}")
    public QueryResponse requestLoan(@PathVariable String customerNumber) {
        QueryResponse score = client.queryScore(customerNumber);
        return score;
    }
    
    @GetMapping("/request-loan/{customerNumber}")
    public QueryResponse createClient(@PathVariable String customerNumber, @RequestParam Double amount ) {
        //invoke requestLoan method in loanService
        //String customerNumber = loanRequest.getCustomer_number();
        QueryResponse queryResponse = service.requestLoan(customerNumber, amount);
        return queryResponse;    
    }
}
