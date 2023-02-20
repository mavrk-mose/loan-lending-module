package io.credable.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.repository.CustomerDAO;
import io.credable.data.model.CustomerModel;

@RestController
public class CustomerController {
    final private CustomerDAO customerDAO;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("check-data")
    public List<CustomerModel> checkCustomer () {
       List<CustomerModel> data = customerDAO.findAll();
        return data;
    }
}
