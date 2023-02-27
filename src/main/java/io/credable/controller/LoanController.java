package io.credable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;

@RestController
public class LoanController {

    private final ScoringClient client;
    
    public LoanController(ScoringClient client) {
        this.client = client;
    }

    record NewRequest(String customer_number, Integer amount) {
    }

    @GetMapping("request-loan/{customerNumber}")
    public QueryResponse requestLoan(@PathVariable String customerNumber) {
        QueryResponse score = client.queryScore(customerNumber);
        return score;
    }
    
    @PostMapping("create-client/{customerNumber}")
    public String createClient(@PathVariable String customerNumber) {
        String token = client.createClient(customerNumber);
        return token;
    }
}
