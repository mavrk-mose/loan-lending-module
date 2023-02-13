package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.credable.data.model.CustomerModel;

public interface customerDAO extends JpaRepository<CustomerModel, String>{
    
}
