package io.credable.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credable.data.external.customer.Customer;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;
import io.credable.data.repository.CustomerDAO;

@Service
public class CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //gets the customer number from the database
    public Optional<CustomerModel> grabCustomer(String customerNumber) {
        return customerDAO.findByCustomerNumber(customerNumber);
    }

    //map response to entity, then stores it to repo
    public CustomerModel storeCustomer (CustomerResponse response) {
        Customer customer = response.getCustomer();
        CustomerModel customerResponse = null;
        //if customer response is not null, map it to customer model
        ModelMapper modelMapper = new ModelMapper();
        customerResponse = modelMapper.map(customer, CustomerModel.class);
        return customerDAO.save(customerResponse);
    }   
}