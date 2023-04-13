package io.credable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Loan;
import io.credable.data.model.QueryResponse;
import io.credable.data.repository.LoanDAO;
import io.credable.services.LoanService;

@RestController
public class LoanController {

    @Autowired
    private final LoanService service;

    @Autowired
    private final LoanDAO loanDAO;

    public LoanController(LoanService service, LoanDAO loanDAO) {
        this.service = service;
        this.loanDAO = loanDAO;
    }

    @PostMapping("/request-loan")
    public QueryResponse loanRequest(@RequestBody Loan requestLoan) {
        String customerNumber = requestLoan.getCustomerNumber();
        QueryResponse queryResponse = service.requestLoan(customerNumber);
        loanDAO.save(requestLoan);
        return queryResponse;    
    }
}