package io.credable.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Loan;
//import io.credable.data.model.Loan;
import io.credable.data.model.QueryResponse;
import io.credable.services.LoanService;

@RestController
public class LoanController {

    private final LoanService service;
    
    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping("/request-loan")
    public QueryResponse loanRequest(@RequestBody Loan requestLoan) {
        String customerNumber = requestLoan.getCustomerNumber();
        QueryResponse queryResponse = service.requestLoan(customerNumber);
        return queryResponse;    
    }

}
