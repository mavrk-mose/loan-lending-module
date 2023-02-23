package io.credable.data.model;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class Client {

    @Autowired
    private RestTemplate restTemplate;
    
    public ResponseEntity<ClientResponse> createClient (ClientRequest request){
        //send request and receive response
        try {
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(request);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/client/createClient");    
            ResponseEntity<ClientResponse> responseEntity = restTemplate.postForEntity(uri, requestEntity, ClientResponse.class);
            return ResponseEntity.ok(responseEntity.getBody());     
        } 
        catch (RestClientException e) {
            return ResponseEntity.badRequest().build(); 
        }
      
    }
}
