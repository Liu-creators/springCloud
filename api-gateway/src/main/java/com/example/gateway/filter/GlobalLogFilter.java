package com.example.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 全局日志过滤器
 * 记录请求路径、响应时间等信息
 */
@Component
public class GlobalLogFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(GlobalLogFilter.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestId = UUID.randomUUID().toString();
        
        // 获取请求信息
        String method = request.getMethodValue();
        URI uri = request.getURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        HttpHeaders headers = request.getHeaders();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String clientIp = remoteAddress != null ? remoteAddress.getHostString() : "unknown";
        
        // 请求开始时间
        LocalDateTime startTime = LocalDateTime.now();
        
        // 打印请求日志
        logger.info("[Gateway] >>> Request[{}] start: {} {} from {} at {}", 
                requestId, method, path + (query != null ? "?" + query : ""), 
                clientIp, formatter.format(startTime));
        
        // 记录请求头信息
        logger.debug("[Gateway] >>> Request[{}] headers: {}", requestId, headers);
        
        // 记录请求参数
        logger.debug("[Gateway] >>> Request[{}] params: {}", requestId, queryParams);

        // 计算处理时间
        long startTimeMillis = System.currentTimeMillis();
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 获取响应信息
            ServerHttpResponse response = exchange.getResponse();
            HttpStatus statusCode = response.getStatusCode();
            HttpHeaders responseHeaders = response.getHeaders();
            
            // 请求结束时间
            LocalDateTime endTime = LocalDateTime.now();
            long duration = System.currentTimeMillis() - startTimeMillis;
            
            // 打印响应日志
            logger.info("[Gateway] <<< Response[{}] end: status={} from {} cost {}ms at {}", 
                    requestId, statusCode, path, duration, formatter.format(endTime));
            
            // 记录响应头信息
            logger.debug("[Gateway] <<< Response[{}] headers: {}", requestId, responseHeaders);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;  // 确保这是第一个执行的过滤器
    }
} 