package io.credable.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credable.data.model.CustomerModel;
import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;
import io.credable.data.repository.CustomerDAO;
import io.micrometer.core.annotation.Timed;
import lombok.SneakyThrows;

@Service
public class LoanService {

    @Autowired
    private CustomerDAO customerDAO;
    
    @Autowired
    private ScoringClient client;
    
    public LoanService(ScoringClient client, 
                       CustomerDAO customerDAO) {
        this.client = client;
        this.customerDAO = customerDAO;
    }
    
    //send loan request
    @SneakyThrows
    @Timed(description = "Time spent to requesting loan", histogram = true)
    public QueryResponse requestLoan (String customerNumber) {
        Optional<CustomerModel> customerOpt = customerDAO.findByCustomerNumber(customerNumber);
        //if customer is in database allow them to request loan
        if (customerOpt.isPresent()) {
            return client.queryScore(customerNumber);
        } else {
            return new QueryResponse(null, customerNumber, null, null, "customer not subscribed", "customer not subscribed");
        }
    }

    public void storeLoan () {
      // TODO find a way to book successful loans
    }
    
}