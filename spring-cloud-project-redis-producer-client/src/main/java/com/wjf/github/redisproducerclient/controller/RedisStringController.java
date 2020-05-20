package com.wjf.github.redisproducerclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;
import com.wjf.github.commons.properties.CodeProperties;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.redisproducerclient.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/redis")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class RedisStringController {

	@Autowired
	private RedisService redisService;

	@PostMapping("/getValue")
	public ResultTemplate<String> getValue(@RequestParam String key) {
		return ResultTemplate.getSuccessResult(redisService.getValue(key));
	}

	@PostMapping("/getValue/prefix")
	public ResultTemplate<String> getValue(@RequestParam BasePrefixKey key, @RequestParam String name) {
		return ResultTemplate.getSuccessResult(redisService.getValue(key, name));
	}

	public ResultTemplate defaultFallBack() {
		log.error("***redis服务挂掉啦！***");
		return ResultTemplate.getFailResult(CodeProperties.REDIS_SERVICE_ERROR, "redis服务失败！", null);
	}
}
