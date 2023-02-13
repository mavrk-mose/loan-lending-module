package io.credable.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.credable.data.model.Subscribe;

@Service
public class SubscribeService {

    public List<Subscribe> subscribeCustomer() {
        return List.of(
            new Subscribe()
        );  
    }
}