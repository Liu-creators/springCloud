package com.example.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign客户端配置类
 * 用于配置Feign客户端的各种参数
 */
@Configuration
public class FeignConfig {
    
    /**
     * 配置Feign的日志级别
     * NONE: 不记录任何日志
     * BASIC: 仅记录请求方法、URL、响应状态码和执行时间
     * HEADERS: 记录BASIC级别的基础上，还记录请求和响应的header
     * FULL: 记录请求和响应的header、body和元数据
     * 
     * @return 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
} 