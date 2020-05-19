package com.wjf.github.userinfoconsumerclient.controller;

import lombok.extern.slf4j.Slf4j;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjf.github.commons.domain.producer.UserInfo;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.userinfoconsumerclient.service.UserInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoFeignService userInfoFeignService;

	@PostMapping("/logIn")
	@HystrixCommand(fallbackMethod = "userLogIn", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),//时间窗口期
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//最大失败比例，在时间窗口期内，失败比例超过将会打开熔断器
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否启用熔断器
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),//超时时间
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1000"),//最大同时允许请求
			@HystrixProperty(name = "execution.timeout.enabled", value = "true")
	})
	public ResultTemplate<UserInfo> logIn(@RequestBody UserInfo userInfo) {
		log.info("***请求了登录接口***");
		return userInfoFeignService.logIn(userInfo);
	}

	public ResultTemplate<UserInfo> userLogIn(@RequestBody UserInfo userInfo) {
		return ResultTemplate.getFailResult(5001, "aaaa", userInfo);
	}
}
