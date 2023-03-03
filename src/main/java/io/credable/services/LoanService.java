package io.credable.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credable.data.model.CustomerModel;
import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;
import io.credable.data.repository.CustomerDAO;
import lombok.SneakyThrows;

@Service
public class LoanService {

    @Autowired
    private CustomerDAO customerDAO;
    
    public ScoringClient client;
    
    public LoanService(ScoringClient client) {
        this.client = client;
    }
    
    @SneakyThrows
    public QueryResponse requestLoan (String customerNumber/* , Double amount */) {
        Optional<CustomerModel> customerOpt = customerDAO.findByCustomerNumber(customerNumber);
        //if customer is in database allow them to request loan
        if (customerOpt.isPresent()) {
            QueryResponse queryScore = client.queryScore(customerNumber);
            return queryScore;
        } else {
            return new QueryResponse(null, customerNumber, null, null, "customer not subscribed", "customer not subscribed");
        }
    }
}
