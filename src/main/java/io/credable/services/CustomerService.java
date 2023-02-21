package io.credable.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.credable.data.external.customer.Customer;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;
import io.credable.data.repository.CustomerDAO;

//TODO: how do I invoke this service inside the controller?
@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final CustomerResponse response;

    public CustomerService(CustomerDAO customerDAO,
                           CustomerResponse response) {
        this.customerDAO = customerDAO;
        this.response = response;
    }

    //gets the customer number from the database
    public CustomerModel grabCustomer(String customer_number) {
        return customerDAO.findCustomerNumber(customer_number);
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