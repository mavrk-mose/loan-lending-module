package io.credable.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> requestLoan(@PathVariable String customerNumber) {
        //TODO: this should have two parameters one of customer number and for token each are required for different methods
        ResponseEntity<?> score = client.initiateQueryScore(customerNumber);
        return score;
    }
    
    @PostMapping("create-client/{customerNumber}")
    public String createClient(@PathVariable String customerNumber) {
        String token = client.createClient(customerNumber);
        return token;
    }
}
