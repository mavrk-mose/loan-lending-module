package io.credable.data.model;

import java.net.URI;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Client {
    RestTemplate restTemplate = new RestTemplate();
    
    public ResponseEntity<ClientResponse> createClient (ClientRequest request){
        //send request and receive response
        try {
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(request);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/client/createClient");    
            ResponseEntity<ClientResponse> responseEntity = restTemplate.postForEntity(uri, requestEntity, ClientResponse.class);
            ClientResponse response = (ClientResponse) Map.of("Response", responseEntity);
            return 
                new ResponseEntity<ClientResponse>(response, HttpStatus.OK);
        } catch (RestClientException e) {
            // handle exception
            return 
                new ResponseEntity<ClientResponse>(HttpStatus.NOT_FOUND);
        }
    }

}
