package com.wjf.github.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosClient01 {

	public static void main(String[] args) {
		SpringApplication.run(NacosClient01.class, args);
	}

}
