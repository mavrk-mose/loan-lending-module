package io.credable.services;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.credable.controller.SubscribeController;
import io.credable.data.external.customer.CustomerResponse;
import io.credable.data.model.CustomerModel;

@Service
public class SubscribeService {
    //maps response to entity
    public ResponseEntity<Object> storeCustomer (String customer_number) throws JAXBException {
        ModelMapper modelMapper = new ModelMapper();
        SubscribeController subscribe = new SubscribeController();
        CustomerResponse response = subscribe.getCustomer(customer_number);
        CustomerModel model = modelMapper.map(response, CustomerModel.class);
        return ResponseEntity.ok(model);
    }
}