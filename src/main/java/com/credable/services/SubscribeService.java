package com.credable.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.credable.model.*;
import com.credable.repository.SubscribeDAO;

@Service
public class SubscribeService {

    private final SubscribeDAO subscribeDAO;
    
    public SubscribeService(SubscribeDAO subscribeDAO) {
        this.subscribeDAO = subscribeDAO;
    }

    public List<Subscribe> getCustomers() {
        /* return List.of(
                new Subscribe(
                    234774784
                )
        ); */
        return subscribeDAO.findAll();
    }

}