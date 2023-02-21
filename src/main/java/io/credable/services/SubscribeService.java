package io.credable.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credable.data.model.CustomerModel;
import io.credable.data.repository.CustomerDAO;

@Service
public class SubscribeService {
    
    @Autowired
    private final CustomerDAO customerDAO;

    public SubscribeService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //saves our entity to repository
    public CustomerModel addCustomer (CustomerModel customer){
        return customerDAO.save(customer);
    }
}