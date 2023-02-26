package io.credable.data.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScoringClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = Logger.getLogger(ScoringClient.class.getName());

    private String clientToken;

    private String token;

    private String customerNumber;

    //POST request to generate client-token
    public ResponseEntity<?> createClient () throws JsonProcessingException{
        //request payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("url","localhost:8080/transactions");
        requestBody.put("name","transactions");
        requestBody.put("username","admin");
        requestBody.put("password","pwd123"); 

        //serialized hashmap to string
        ObjectMapper mapper = new ObjectMapper();
        String request = mapper.writeValueAsString(requestBody);
        try {
            //send request and receive response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); 
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, headers);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/client/createClient");    
            ResponseEntity<ClientResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ClientResponse.class);
            //extract value of client-token
            if (responseEntity != null) {
                ModelMapper modelMapper = new ModelMapper();
                ClientResponse response = modelMapper.map(responseEntity.getBody(), ClientResponse.class);
                String token = response.getToken();
                this.clientToken = token; //makes the client-token as a global variable
                   return ResponseEntity.ok(this.clientToken);
            } else {
                LOGGER.warning("The response is empty");
                   return ResponseEntity.badRequest().build();
            }  
        } catch (RestClientException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException("failed to send POST request");
        }         
    }
    
    //initialize the query score
    private void initiateQueryScore (String customerNumber) throws JsonProcessingException {
        //if client-token is not null, use client-token as header
       if (this.clientToken != null) {
            try {
                //create a GET request with client-token as header
                URI uri = URI.create("https://scoringtest.credable.io/ap1/v1/scoring/initiateQueryScore/{customerNumber}");
                HttpHeaders headers = new HttpHeaders();
                headers.set("client-token", this.clientToken);
                headers.setContentType(MediaType.APPLICATION_JSON); 
                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<Token> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Token.class);

                //extract token value 
                if (response != null) {
                    Token responseToken = null;
                    ModelMapper modelMapper = new ModelMapper();
                    responseToken = modelMapper.map(response, Token.class);
                    String newToken = responseToken.getToken();
                    this.token = newToken;
                } else {
                    LOGGER.warning("token failed to generate");
                }
            } catch (RestClientException e) {
                LOGGER.warning(e.getMessage());
            }
        } else { 
        //otherwise trigger createClient() to create client-token
         createClient();
       }     
    }

    //query the score
    public QueryResponse queryScore (String token) throws JsonProcessingException {
        //if token & client-token are not null trigger GET request
        if (this.token != null && this.clientToken != null) {
            try {
                //create a GET request with client-token as header
                String queryToken = this.token;
                URI uri = URI.create("https://scoringtest.credable.io/ap1/v1/scoring/queryScore/" + queryToken);
                HttpHeaders headers = new HttpHeaders();
                headers.set("client-token", this.clientToken);
                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<QueryResponse> score = restTemplate.exchange(uri, HttpMethod.GET, entity,QueryResponse.class);
                
                //map response to expected respone
                if (score != null) {
                    QueryResponse queryResponse = null;
                    ModelMapper modelMapper = new ModelMapper();
                    queryResponse = modelMapper.map(score, QueryResponse.class);
                    return queryResponse;
                }else{
                    LOGGER.warning("response was empty");
                }
            } catch (RestClientException e) {
               LOGGER.log(Level.SEVERE, "error occurred", e);
            }
        } else {
            //otherwise trigger initiateQueryScore to generate tokenss
            initiateQueryScore(customerNumber);            
         }
    throw new RuntimeException("Failed to retrieve data from server");
   }
}