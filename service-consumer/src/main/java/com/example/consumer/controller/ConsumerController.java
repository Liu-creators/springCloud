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
    
    // 添加一个新的端点，用于处理从网关转发过来的请求
    @GetMapping("/hello")
    public String callProviderFromGateway() {
        return "来自网关的请求: " + providerClient.getHello();
    }
} 