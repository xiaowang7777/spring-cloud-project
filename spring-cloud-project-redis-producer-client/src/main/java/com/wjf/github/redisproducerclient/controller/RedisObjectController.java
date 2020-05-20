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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/redis")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class RedisObjectController {

	@Autowired
	private RedisService redisService;

	@PostMapping("/getObject")
	public <T> ResultTemplate<T> getObject(@RequestParam String key, @RequestParam Class<T> clazz) {
		return ResultTemplate.getSuccessResult(redisService.getObject(key, clazz));
	}

	@PostMapping("/getObject/prefix")
	public <T> ResultTemplate<T> getObject(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam Class<T> clazz) {
		return ResultTemplate.getSuccessResult(redisService.getObject(key, name, clazz));
	}

	@PostMapping("/getList")
	public <T> ResultTemplate<List<T>> getList(@RequestParam String key, @RequestParam Class<T> clazz) {
		return ResultTemplate.getSuccessResult(redisService.getList(key, clazz));
	}

	@PostMapping("/getList/prefix")
	public <T> ResultTemplate<List<T>> getList(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam Class<T> clazz) {
		return ResultTemplate.getSuccessResult(redisService.getList(key, name, clazz));
	}

	public ResultTemplate defaultFallBack() {
		log.error("***redis服务挂掉啦！***");
		return ResultTemplate.getFailResult(CodeProperties.REDIS_SERVICE_ERROR, "redis服务失败！", null);
	}

}
