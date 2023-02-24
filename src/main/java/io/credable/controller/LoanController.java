package io.credable.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Loan;
import io.credable.data.model.ScoringClient;

@RestController
public class LoanController {

    private final ScoringClient client;
    
    record NewRequest(String customer_number, Integer amount) {
    }


    @GetMapping("request-loan")
    public ResponseEntity<Object> requestLoan (@RequestBody NewRequest request) {
        
         return null;
    }
    
    

}
