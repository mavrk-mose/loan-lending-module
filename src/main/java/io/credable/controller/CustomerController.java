package io.credable.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.credable.data.model.Subscribe;
import io.credable.data.repository.SubscribeDAO;

@RestController
public class CustomerController {
    
    private final SubscribeDAO subscribeDAO;

    public CustomerController(SubscribeDAO subscribeDAO) {
        this.subscribeDAO = subscribeDAO;
    }

    @GetMapping("getNumber")
    public List<Subscribe> getCustomerNumber () {
        return subscribeDAO.findAll();
    }
}
