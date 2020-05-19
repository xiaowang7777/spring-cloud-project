package com.wjf.github.producerclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjf.github.commons.domain.producer.UserInfo;
import com.wjf.github.commons.properties.CodeProperties;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.producerclient.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
	private UserInfoService userInfoService;

	@PostMapping("/logIn")
	@HystrixCommand(fallbackMethod = "userLogIn", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),//时间窗口期
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//最大失败比例，在时间窗口期内，失败比例超过将会打开熔断器
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否启用熔断器
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),//超时时间
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1000")//最大同时允许请求
	})
	public ResultTemplate<UserInfo> logIn(@RequestBody UserInfo userInfo) {
		log.info("***用户登录***");
		return ResultTemplate.getSuccessResult(userInfoService.logIn(userInfo));
	}

	public ResultTemplate<UserInfo> userLogIn(UserInfo userInfo) {
		log.info("***用户登录接口报错:" + userInfo.toString() + "***");
		return ResultTemplate.getFailResult(CodeProperties.LOG_ERROR, "登录处理错误！", userInfo);
	}
}
