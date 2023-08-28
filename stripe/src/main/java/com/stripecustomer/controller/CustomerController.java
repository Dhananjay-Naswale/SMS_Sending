package com.stripecustomer.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripecustomer.Payload.CustomerRequest;
import com.stripecustomer.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final StripeService stripeService;

    @Autowired
    public CustomerController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest request) {
        try {
            Customer customer = stripeService.createCustomer(request.getName(), request.getEmail());
            return new ResponseEntity<>("Customer created successfully. ID: " + customer.getId(), HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

