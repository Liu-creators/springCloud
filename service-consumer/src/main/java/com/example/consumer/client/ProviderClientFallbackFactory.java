package com.example.consumer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务提供者Feign客户端降级工厂
 * 相比于Fallback，FallbackFactory可以获取到具体的异常信息
 */
@Component
public class ProviderClientFallbackFactory implements FallbackFactory<ProviderClient> {
    
    private static final Logger logger = LoggerFactory.getLogger(ProviderClientFallbackFactory.class);
    
    @Override
    public ProviderClient create(Throwable cause) {
        // 记录异常信息
        logger.error("服务调用异常", cause);
        
        // 返回降级实现
        return new ProviderClient() {
            @Override
            public String getHello() {
                return "Fallback (Factory): Service temporarily unavailable. " 
                        + "Error: " + cause.getMessage();
            }
        };
    }
} 