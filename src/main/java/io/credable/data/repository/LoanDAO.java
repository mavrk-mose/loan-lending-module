package io.credable.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.credable.data.model.Loan;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Long>{
    Optional<Loan> findByCustomerNumber(String customerNumber); 
    Loan findAmountByCustomerNumber(String customerNumber);
}
