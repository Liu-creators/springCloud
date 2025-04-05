package com.example.consumer.client;

import com.example.consumer.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务提供者Feign客户端接口
 * 
 * @FeignClient 注解说明：
 * name: 指定服务提供者的服务名称
 * fallbackFactory: 指定服务降级处理工厂，可以获取异常信息
 * configuration: 指定Feign配置类
 */
@FeignClient(name = "provider-service", 
    fallbackFactory = ProviderClientFallbackFactory.class,
    configuration = FeignConfig.class)
public interface ProviderClient {
    
    /**
     * 调用服务提供者的hello接口
     * @return 服务提供者返回的字符串
     */
    @GetMapping("/api/hello")
    String getHello();
} 