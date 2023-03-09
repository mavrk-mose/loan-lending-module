package io.credable.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credable.data.model.Loan;
import io.credable.data.model.QueryResponse;
import io.credable.data.model.Status;
import io.credable.data.repository.LoanDAO;
import io.credable.data.repository.QueryResponseDAO;
@Service
public class StatusService {

    @Autowired
    private final QueryResponseDAO queryResponseDAO;

    @Autowired
    private final LoanDAO loanDAO;

    public StatusService(QueryResponseDAO queryResponseDAO, LoanDAO loanDAO) {
        this.queryResponseDAO = queryResponseDAO;
        this.loanDAO = loanDAO;
    }

    public String checkLoanStatus (String customerNumber) {
        Optional<Loan> loanOpt = loanDAO.findByCustomerNumber(customerNumber);
        //grabbing value of limitAmount
        QueryResponse queryResponse = queryResponseDAO.findLimitAmountByCustomerNumber(customerNumber);
        Double limitAmount = 0.0;
        if (queryResponse != null) {
            limitAmount = queryResponse.getLimitAmount();
        }
        //grabbing value of requested amount
        Loan loan = loanDAO.findAmountByCustomerNumber(customerNumber);
        Double amount = 0.0;
        if (loan != null) {
            amount = loan.getAmount();
        }
        String loanStatus;

        //if there is a loan associated with the customerNumber
       if (loanOpt.isPresent() && amount != null && limitAmount != null) {
            if (limitAmount >= amount) {
                //TODO: find a way to book successful loans for auditors
                loanStatus = String.valueOf(Status.SUCCESSFUL);
                return loanStatus;
            } else {
                //TODO: I'll have to create a pending status 
                loanStatus = String.valueOf(Status.REJECTED);
                return loanStatus;
            }
        } else {
         return "customer did not request loan";
       }
    }
}
