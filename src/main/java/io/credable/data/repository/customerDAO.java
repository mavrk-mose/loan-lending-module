package io.credable.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import io.credable.data.model.CustomerModel;

public interface CustomerDAO extends JpaRepository<CustomerModel, Integer> {

    public List<CustomerModel> findByCustomerNumber(String customerNumber);;
    
}
