package com.credable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credable.model.Subscribe;

public interface SubscribeDAO extends JpaRepository<Subscribe, Long>{
    
}

