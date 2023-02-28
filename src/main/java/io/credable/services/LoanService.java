package io.credable.services;

import org.springframework.stereotype.Service;

import io.credable.data.model.QueryResponse;
import io.credable.data.model.ScoringClient;

@Service
public class LoanService {

    public ScoringClient client;
    
    public LoanService(ScoringClient client) {
        this.client = client;
    }

    public QueryResponse requestLoan (String customerNumber) {
        //trigger query score from scoringclient
        QueryResponse queryScore = client.queryScore(customerNumber);
        //somehow the loan request needs to be persisted on the db
        return queryScore;
    }
}
