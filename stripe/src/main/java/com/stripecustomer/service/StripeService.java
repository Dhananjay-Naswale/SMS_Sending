package com.stripecustomer.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public Customer createCustomer(String name, String email) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);

        return Customer.create(params);
    }
}
