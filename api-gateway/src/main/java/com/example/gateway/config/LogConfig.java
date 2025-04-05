package com.example.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 日志配置类
 * 用于确保日志目录存在
 */
@Configuration
public class LogConfig {
    private static final Logger logger = LoggerFactory.getLogger(LogConfig.class);
    
    @PostConstruct
    public void init() {
        // 确保日志目录存在
        File logDir = new File("logs");
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                logger.info("Created log directory: {}", logDir.getAbsolutePath());
            } else {
                logger.warn("Failed to create log directory: {}", logDir.getAbsolutePath());
            }
        } else {
            logger.info("Log directory already exists: {}", logDir.getAbsolutePath());
        }
    }
} 