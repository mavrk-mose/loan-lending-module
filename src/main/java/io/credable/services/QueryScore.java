package io.credable.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class QueryScore {

    //request payload
    Map<String, Object> request = new HashMap<>();

    RestTemplate restTemplate = new RestTemplate();
    //Initiate Query Score
    
    String url = "https://scoringtest.credable.io/api/v1/scoring/initiateQueryScore/{customerNumber}";
    
    //Query the Score

}
