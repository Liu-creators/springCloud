package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 日志控制器
 * 用于查看网关日志
 */
@RestController
@RequestMapping("/logs")
public class LogController {

    @Value("${logging.file.name:logs/api-gateway.log}")
    private String logFilePath;

    /**
     * 获取最近的日志
     * @param lines 获取的行数，默认100行
     * @return 日志内容
     */
    @GetMapping("/recent")
    public ResponseEntity<List<String>> getRecentLogs(@RequestParam(defaultValue = "100") int lines) {
        File logFile = new File(logFilePath);
        if (!logFile.exists() || !logFile.isFile()) {
            return ResponseEntity.ok(Collections.singletonList("Log file not found: " + logFilePath));
        }

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            List<String> allLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
            
            // 获取最后的lines行
            int startIndex = Math.max(0, allLines.size() - lines);
            for (int i = startIndex; i < allLines.size(); i++) {
                result.add(allLines.get(i));
            }
            
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.ok(Collections.singletonList("Error reading log file: " + e.getMessage()));
        }
    }

    /**
     * 获取网关日志信息
     * @return 日志文件信息
     */
    @GetMapping("/info")
    public ResponseEntity<String> getLogInfo() {
        File logFile = new File(logFilePath);
        if (!logFile.exists() || !logFile.isFile()) {
            return ResponseEntity.ok("Log file not found: " + logFilePath);
        }
        
        StringBuilder info = new StringBuilder();
        info.append("Log file: ").append(logFile.getAbsolutePath()).append("\n");
        info.append("Size: ").append(logFile.length() / 1024).append(" KB").append("\n");
        info.append("Last modified: ").append(new java.util.Date(logFile.lastModified()));
        
        return ResponseEntity.ok(info.toString());
    }
} 