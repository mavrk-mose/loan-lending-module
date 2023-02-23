package io.credable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Client;
import io.credable.data.model.ClientRequest;
import io.credable.data.model.ClientResponse;

//import io.credable.data.model.Loan;

@RestController
public class LoanController {

    // record NewRequest(String customer_number, Integer amount) {
    // }
    @Autowired
    private final Client client;

    public LoanController(Client client) {
        this.client = client;
    }


    // @GetMapping("request-loan")
    // public ResponseEntity<Object> requestLoan (@RequestBody NewRequest request) {
    //     Loan loan = new Loan();
    //     loan.setCustomerNumber(request.customer_number);
    //     loan.setAmount(request.amount);
    //     return 
    //         new ResponseEntity<>("successful loan request", HttpStatus.OK);
    // }
    @PostMapping("registerClient")
    public ResponseEntity<Object> getClientToken (@RequestBody ClientRequest request) {
        ResponseEntity<ClientResponse> register = client.createClient(request);
        return 
            new ResponseEntity<>(register, HttpStatus.OK);
    }

}
