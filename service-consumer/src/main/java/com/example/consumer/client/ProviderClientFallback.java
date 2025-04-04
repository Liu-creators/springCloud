package com.example.consumer.client;

import org.springframework.stereotype.Component;

@Component
public class ProviderClientFallback implements ProviderClient {
    @Override
    public String getHello() {
        return "Fallback: Service temporarily unavailable";
    }
} 