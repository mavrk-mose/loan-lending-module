package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.credable.data.model.Status;

public interface StatusDAO extends JpaRepository<Status, Integer>{
    
}
