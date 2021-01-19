package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author HLC
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
/**
 * 对Hystrix熔断机制的支持
 */
@EnableCircuitBreaker
public class DeptProvider8001App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001App.class,args);
    }
}
