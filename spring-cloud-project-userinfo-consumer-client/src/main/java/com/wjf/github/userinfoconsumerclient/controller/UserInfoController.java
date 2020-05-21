package com.wjf.github.userinfoconsumerclient.controller;

import com.wjf.github.commons.domain.redis.prefixkey.UserTokenPrefixKey;
import com.wjf.github.commons.domain.redis.vo.RedisVo;
import com.wjf.github.commons.properties.CodeProperties;
import com.wjf.github.commons.util.MD5Util;
import com.wjf.github.userinfoconsumerclient.service.RedisService;
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

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoFeignService userInfoFeignService;

	@Autowired
	private RedisService redisService;

	@PostMapping("/logIn")
	@HystrixCommand(fallbackMethod = "userLogIn", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000"),//短路多久过后尝试恢复
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//最大失败比例，在时间窗口期内，失败比例超过将会打开熔断器
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否启用熔断器
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),//超时时间
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1000"),//最大同时允许请求
			@HystrixProperty(name = "execution.timeout.enabled", value = "true")
	})
	public ResultTemplate<UserInfo> logIn(@RequestBody UserInfo userInfo) {
		log.info("***请求了登录接口***");

		final ResultTemplate<UserInfo> user = userInfoFeignService.logIn(userInfo);
		if (user.getCode().equals(CodeProperties.LOG_ERROR) || CodeProperties.LOG_USER_NAME_ERROR.equals(user.getCode())) {
			return user;
		}
		final String md5Str = MD5Util.getMD5Str(new Date().getTime() + "_" + user.getT().getUserId());
		RedisVo redisVo = new RedisVo<>();
		redisVo.setK(UserTokenPrefixKey.getUserTokenPrefixKey(60 * 60 * 24));
		redisVo.setKey(user.getT().getUserId() + "");
		redisVo.setValue(md5Str);
		if (redisService.setKey(redisVo).getT()) {
			return user;
		}
		log.error("***redis缓存失败***");
		return ResultTemplate.getFailResult(CodeProperties.LOG_ERROR, "redis缓存失败！", null);
	}

	public ResultTemplate<UserInfo> userLogIn(@RequestBody UserInfo userInfo) {
		return ResultTemplate.getFailResult(5001, "aaaa", userInfo);
	}
}
