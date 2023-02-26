package io.credable.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.ScoringClient;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoanController {

    private final ScoringClient client;
    
    public LoanController(ScoringClient client) {
        this.client = client;
    }

    record NewRequest(String customer_number, Integer amount) {
    }

    @GetMapping("request-loan")
    public ResponseEntity<Object> requestLoan(@RequestBody String customerNumber) throws JsonProcessingException {
        //TODO: this should have two parameters one of customer number and for token each are required for different methods
         ResponseEntity<?> score = client.initiateQueryScore(customerNumber);
         return ResponseEntity.ok(score);
    }
    
    

}
