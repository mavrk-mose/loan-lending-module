package io.credable.data.model;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class ScoringClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = Logger.getLogger(ScoringClient.class.getName());

    private String clientToken;

    @SneakyThrows
    private static void delay(int seconds) {
        Thread.sleep(seconds * 1000);
    }

    //POST request to generate client-token
    @SneakyThrows
    private String createClient (String customerNumber){
        //request payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("url","http://mossestest.credable.io:9090/query/"+ customerNumber);
        requestBody.put("name","");
        requestBody.put("username","");
        requestBody.put("password",""); 

        //serialize hashmap to string
        ObjectMapper mapper = new ObjectMapper();
        String request = mapper.writeValueAsString(requestBody);
        try {
            //send request and receive response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); 
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, headers);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/client/createClient");    
            ResponseEntity<ClientResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ClientResponse.class);
            //extract value of client-token with objectMapper
            if (responseEntity != null && responseEntity.getBody() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseJson = objectMapper.writeValueAsString(responseEntity.getBody());
                Map<String, String> responseMap = objectMapper.readValue(responseJson, new TypeReference<Map<String, String>>() {});
                String token = (String) responseMap.get("token");
                this.clientToken = token; //makes the client-token as a global variable
                return this.clientToken;
            } else {
                LOGGER.warning("The response is empty");
                return "response failed to generate";
            }  
        } catch (RestClientException e) {
            LOGGER.warning(e.getMessage());
            return "failed to send GET request";
        }         
    }

    //initialize the query score
    @SneakyThrows
    private ResponseEntity<String> initiateQueryScore (String customerNumber) {
        try {
            //create a GET request with client-token as header
            String clientToken = createClient(customerNumber);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/scoring/initiateQueryScore/" + customerNumber);
            HttpHeaders headers = new HttpHeaders();
            headers.set("client-token", clientToken);
            headers.setContentType(MediaType.APPLICATION_JSON); 
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.warning(e.getMessage());
            return new ResponseEntity<String>("failed to send GET request", HttpStatus.BAD_REQUEST);
        }   
    }

    //query the score
    @SneakyThrows
    public QueryResponse queryScore (String customerNumber) {
        try {
            ResponseEntity<String> queryToken = initiateQueryScore(customerNumber);
            String token = queryToken.getBody();           
            String clientToken = createClient(customerNumber);
            URI uri = URI.create("https://scoringtest.credable.io/api/v1/scoring/queryScore/" + token);
            HttpHeaders headers = new HttpHeaders();
            headers.set("client-token", clientToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            //wait and retry
            int maxRetries = 5;
            int retry = 0;
            QueryResponse response = null;
            while (response == null && retry < maxRetries) {
                delay(20); 
                ResponseEntity<QueryResponse> score = restTemplate.exchange(uri, HttpMethod.GET, entity,QueryResponse.class);
                response = score.getBody();
                retry++;
            }
            //map response to expected response
            if (response != null) {
                ModelMapper modelMapper = new ModelMapper();
                QueryResponse queryResponse = modelMapper.map(response, QueryResponse.class);
                return queryResponse;
            }else{
                LOGGER.warning("response was empty");
                return new QueryResponse(null, customerNumber, null, null, "empty", "empty");
            }
        } catch (RestClientException e) {
            LOGGER.warning(e.getMessage());
            return new QueryResponse(null, customerNumber, null, null, "pending..", "pending..");
        }
   }
}