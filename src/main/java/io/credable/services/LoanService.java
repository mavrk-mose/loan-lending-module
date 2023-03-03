package io.credable.services;

import org.springframework.stereotype.Service;

import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;
import lombok.SneakyThrows;

@Service
public class LoanService {

    public ScoringClient client;
    
    public LoanService(ScoringClient client) {
        this.client = client;
    }
    
    @SneakyThrows
    public QueryResponse requestLoan (String customerNumber/* , Double amount */) {
        //trigger query score from scoringclient
        QueryResponse queryScore = client.queryScore(customerNumber);
        return queryScore;
    }
}
