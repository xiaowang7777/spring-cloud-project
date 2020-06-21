package com.wjf.github.springcloud.alibaba.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class NaocsConsumer01 {
	public static void main(String[] args) {
		SpringApplication.run(NaocsConsumer01.class, args);
	}
}
