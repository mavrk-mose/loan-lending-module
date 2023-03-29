package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.credable.data.model.QueryResponse;

public interface QueryResponseDAO extends JpaRepository<QueryResponse, Integer> {
    QueryResponse findLimitAmountByCustomerNumber(String customerNumber);
}
