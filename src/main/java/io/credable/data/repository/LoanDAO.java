package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.credable.data.model.Loan;

public interface LoanDAO extends JpaRepository<Loan, Integer>{
    
}
