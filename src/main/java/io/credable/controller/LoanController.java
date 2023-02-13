package io.credable.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Loan;
import io.credable.data.repository.LoanDAO;

@RestController
public class LoanController {
    
    private final LoanDAO loanDAO;

    public LoanController(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    record NewRequest(
        String customer_number,
        Integer amount
    ) {
    }

    @PostMapping("request-loan")
    public void requestLoan (@RequestBody NewRequest request) {
        Loan loan = new Loan();
        loan.setCustomerNumber(request.customer_number);
        loan.setAmount(request.amount);
        loanDAO.save(loan);
    }

}
