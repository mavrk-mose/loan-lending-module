package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.credable.data.model.QueryResponse;

@Repository
public interface QueryResponseDAO extends JpaRepository<QueryResponse, Integer> {
    QueryResponse findLimitAmountByCustomerNumber(String customerNumber);
}
