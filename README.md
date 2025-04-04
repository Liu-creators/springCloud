# SpringCloud 服务注册与发现 Demo

这是一个基于SpringCloud的服务注册与发现Demo项目，使用Eureka作为注册中心，Feign作为服务调用工具。

## 项目结构

```
springcloud-demo/
├── eureka-server/           # Eureka注册中心
├── service-provider/        # 服务提供者
├── service-consumer/        # 服务消费者
└── api-gateway/            # API网关
```

## 技术栈

- Spring Boot 2.7.0
- Spring Cloud 2021.0.3
- Eureka Server/Client
- OpenFeign
- Spring Cloud LoadBalancer
- Spring Boot Actuator

## 功能特性

- 服务注册与发现
- 服务间调用
- 负载均衡
- 服务健康检查
- API网关
- 服务降级

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 启动步骤

1. 启动Eureka注册中心（两个实例）：
```bash
cd eureka-server
mvn spring-boot:run -Dspring.profiles.active=peer1
```
在另一个终端：
```bash
cd eureka-server
mvn spring-boot:run -Dspring.profiles.active=peer2
```

2. 启动服务提供者：
```bash
cd service-provider
mvn spring-boot:run
```

3. 启动服务消费者：
```bash
cd service-consumer
mvn spring-boot:run
```

4. 启动API网关：
```bash
cd api-gateway
mvn spring-boot:run
```

### 服务访问

- Eureka控制台：
  - http://127.0.0.1:8761
  - http://127.0.0.1:8762

- 服务提供者健康检查：
  - http://127.0.0.1:8081/actuator/health

- 服务消费者健康检查：
  - http://127.0.0.1:8082/actuator/health

- 服务调用测试：
  - http://127.0.0.1:8082/consumer/hello

## 配置说明

### Eureka配置

- 服务注册：`eureka.client.register-with-eureka=true`
- 获取注册表：`eureka.client.fetch-registry=true`
- 心跳间隔：`eureka.instance.lease-renewal-interval-in-seconds=2`
- 租约过期时间：`eureka.instance.lease-expiration-duration-in-seconds=5`

### 服务提供者配置

- 服务端口：8081
- 服务名称：provider-service
- 健康检查路径：/actuator/health

### 服务消费者配置

- 服务端口：8082
- 服务名称：consumer-service
- Feign客户端配置：支持服务降级

## 项目特点

1. 高可用注册中心
   - 双节点Eureka Server
   - 相互注册实现高可用

2. 服务健康监控
   - Spring Boot Actuator集成
   - 详细的健康检查信息

3. 服务调用
   - Feign声明式REST客户端
   - 负载均衡支持
   - 服务降级处理

4. 配置管理
   - 统一的配置中心
   - 环境隔离配置

## 进阶功能

1. 服务熔断
2. 服务限流
3. 分布式配置
4. 服务链路追踪

## 注意事项

1. 确保所有服务都已正确注册到Eureka
2. 检查服务健康状态
3. 注意服务调用的超时设置
4. 合理配置服务降级策略

## 常见问题

1. 服务注册失败
   - 检查Eureka服务器是否正常运行
   - 检查网络连接
   - 检查服务配置

2. 服务调用失败
   - 检查服务是否已注册
   - 检查服务健康状态
   - 检查网络连接

## 贡献指南

1. Fork 本仓库
2. 创建新的分支
3. 提交代码
4. 创建 Pull Request

## 许可证

MIT License 