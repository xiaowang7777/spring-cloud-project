package com.springcloud.userCenter;

import com.wjf.github.commons.service.RedisService;
import com.wjf.github.commons.service.impl.RedisServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class UserCenter {

	public static void main(String[] args) {
		SpringApplication.run(UserCenter.class, args);
	}

	@Bean
	public RedisService redisService(){
		return new RedisServiceImpl();
	}

}
