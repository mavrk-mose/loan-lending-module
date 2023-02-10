package io.credable.controller;

import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Subscribe;
import io.credable.data.repository.SubscribeDAO;
import io.credable.services.SubscribeService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class SubscribeController {

    private SubscribeService subscribeService;

    private SubscribeDAO subscribeDAO;

    public SubscribeController(SubscribeDAO subscribeDAO) {
        this.subscribeDAO = subscribeDAO;
    }

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @GetMapping("/subscribe")
    public List<Subscribe> getCustomers() {
            return subscribeService.getCustomers();
    }
    
    record NewSubscription (Integer customer_id) {

    }

    @PostMapping("/subscribe")
    public void subscribeCustomer (NewSubscription subscription){
        Subscribe subscribe = new Subscribe();
        subscribe.setCustomer_id(subscription.customer_id());
        subscribeDAO.findAll();
    }
}
