package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Mono;

/**
 * API网关配置类
 * 提供限流、跨域等配置
 */
@Configuration
public class GatewayConfig {

    /**
     * IP地址限流器的Key解析器
     * 基于请求的远程地址（IP）进行限流
     * 
     * 注意：此Bean仅在启用了redis限流配置的环境中生效
     * 目前已禁用Redis限流，此Bean不会被创建
     *
     * @return IP键解析器
     */
    @Bean
    @Profile("redis") // 只在redis profile激活时才创建此Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest().getRemoteAddress().getHostString()
        );
    }
} 