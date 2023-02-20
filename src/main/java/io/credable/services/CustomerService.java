package io.credable.services;

import org.springframework.stereotype.Service;

import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.repository.customerDAO;
@Service
public class CustomerService {
    
    //TODO: persist response data to KYC database, by saving to repository
    public customerDAO getCustomer (String customer_number) {
       CustomerClient customerClient = new CustomerClient();
       CustomerResponse request = customerClient.getCustomer(customer_number);
       return (customerDAO) request;
    //TODO: print out subscribed successfully, otherwise print out sign in

    }
}
