package com.wjf.github.springcloud.alibaba.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class GateWay01 {
	public static void main(String[] args) {
		SpringApplication.run(GateWay01.class, args);
	}
}
