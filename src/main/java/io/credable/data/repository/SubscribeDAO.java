package io.credable.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.credable.data.model.Subscribe;

public interface SubscribeDAO 
        extends JpaRepository<Subscribe, Integer> {
    
}

 