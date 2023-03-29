package io.credable.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import io.credable.data.model.CustomerModel;

@Component
public interface CustomerDAO extends JpaRepository<CustomerModel, Integer> {

    Optional<CustomerModel> findByCustomerNumber(String customerNumber);
}
