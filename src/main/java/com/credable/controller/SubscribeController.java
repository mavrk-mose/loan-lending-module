package com.credable.controller;

import org.springframework.web.bind.annotation.RestController;

import com.credable.model.Subscribe;
import com.credable.services.SubscribeService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @GetMapping("/subscribe")
    public List<Subscribe> getCustomers() {
            return subscribeService.getCustomers();
    }

}
