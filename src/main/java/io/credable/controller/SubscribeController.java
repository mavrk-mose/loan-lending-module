package io.credable.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Subscribe;
import io.credable.data.repository.SubscribeDAO;

@RestController
public class SubscribeController {

    private final SubscribeDAO subscribeDAO;
    
    public SubscribeController(SubscribeDAO subscribeDAO) {
        this.subscribeDAO = subscribeDAO; 
    } 
 
    record NewSubscription (
        String customer_number
    ) {
    }

    @PostMapping("subscribe")
    public void subscribeCustomer (@RequestBody NewSubscription subscription) {
        Subscribe subscribe = new Subscribe();
        subscribe.setCustomerNumber(subscription.customer_number);;
        subscribeDAO.save(subscribe);
    }
    
}
