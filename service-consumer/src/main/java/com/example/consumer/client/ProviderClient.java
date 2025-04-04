package com.example.consumer.client;

import com.example.consumer.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-service", 
    fallback = ProviderClientFallback.class,
    configuration = FeignConfig.class)
public interface ProviderClient {
    @GetMapping("/api/hello")
    String getHello();
} 