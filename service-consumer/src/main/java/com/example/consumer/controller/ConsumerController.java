package com.example.consumer.controller;

import com.example.consumer.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/consumer/hello")
    public String callProvider() {
        return providerClient.getHello();
    }
} 