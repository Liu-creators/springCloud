package com.example.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-service")
public interface ProviderClient {
    @GetMapping("/api/hello")
    String getHello();
} 