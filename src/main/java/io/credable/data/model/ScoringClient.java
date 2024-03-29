package io.credable.data.model;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.credable.data.repository.QueryResponseDAO;
import io.micrometer.core.annotation.Timed;
import lombok.SneakyThrows;

@Configuration
public class ScoringClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private final QueryResponseDAO queryResponseDAO;

    public ScoringClient(QueryResponseDAO queryResponseDAO) {
        this.queryResponseDAO = queryResponseDAO;
    }

    private static final Logger LOGGER = Logger.getLogger(ScoringClient.class.getName());

    //POST request to generate client-token
    @SneakyThrows
    private String createClient (String customerNumber){
        String clientToken;
        //request payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("url","http://ec2-3-115-8-202.ap-northeast-1.compute.amazonaws.com:443/query/"+ customerNumber);
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
                clientToken = token; //makes the client-token as a global variable
                return clientToken;
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
            String baseUri = "https://scoringtest.credable.io/api/v1/scoring/";
            String url = "initiateQueryScore/" + customerNumber;
            URI uri = URI.create(baseUri).resolve(url);
            HttpHeaders headers = new HttpHeaders();
            headers.set("client-token", clientToken);
            headers.setContentType(MediaType.APPLICATION_JSON); 
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            LOGGER.warning(e.getMessage());
            return new ResponseEntity<String>("failed to send GET request", HttpStatus.BAD_REQUEST);
        }   
    }

    //query the score
    @SneakyThrows
    @Timed(description = "Time spent to querying score", histogram = true)
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
            while(response == null && retry < maxRetries){
                Thread.sleep(15000); //milliseconds
                ResponseEntity<QueryResponse> score = restTemplate.exchange(uri, HttpMethod.GET, entity,QueryResponse.class);
                response = score.getBody();
                retry++;
            }
            
            //map response to expected response
            if (response!= null) {
                ModelMapper modelMapper = new ModelMapper();
                QueryResponse queryResponse = modelMapper.map(response, QueryResponse.class);
                return queryResponseDAO.save(queryResponse);
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