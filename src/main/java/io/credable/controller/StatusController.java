package io.credable.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Loan;
import io.credable.data.repository.LoanDAO;

@RestController
public class StatusController {
    
    private final LoanDAO loanDAO;
    
    public StatusController(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    @GetMapping("loan-status")
    public List<Loan> checkStatus () {
        return loanDAO.findAll();
    }
}
