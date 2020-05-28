package com.wjf.github.springcloudproject.mqstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MQStreamMain {

	public static void main(String[] args) {
		SpringApplication.run(MQStreamMain.class,args);
	}

}
