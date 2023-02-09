package com.credable.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.credable.model.*;

@Service
public class SubscribeService {
    public List<Subscribe> getCustomers() {
        return List.of(
                new Subscribe(
                    234774784
                )
        );
    }

}