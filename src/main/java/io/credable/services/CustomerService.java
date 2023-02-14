package io.credable.services;

//import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    //make soapservice
    public String soapRequest(String customer_number){
        
        //TODO: inject the customerconfig 

        //TODO: invoke request method to get data

        //TODO: access response data then return it as List.of()

        return customer_number;
    }

    //TODO: write exception handling in case data for that user is not present

    //TODO: persist response data to KYC database, by saving to repository

    //TODO: print out subscribed successfully, otherwise print out sign in

}
