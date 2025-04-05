package com.example.consumer.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
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
    
    /**
     * 配置Feign的超时时间
     * connectTimeout: 连接超时时间
     * readTimeout: 读取超时时间
     * 
     * @return 请求选项
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
    
    /**
     * 配置Feign的重试机制
     * period: 重试间隔
     * maxPeriod: 最大重试间隔
     * maxAttempts: 最大重试次数
     * 
     * @return 重试器
     */
    @Bean
    public Retryer feignRetryer() {
        // 重试间隔为100ms，最大重试间隔为1s，最多重试3次
        return new Retryer.Default(100, 1000, 3);
    }
} 