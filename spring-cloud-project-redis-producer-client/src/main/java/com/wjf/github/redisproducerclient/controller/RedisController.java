package com.wjf.github.redisproducerclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.wjf.github.commons.domain.redis.behavior.EXPX;
import com.wjf.github.commons.domain.redis.behavior.NXXX;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;
import com.wjf.github.commons.domain.redis.vo.RedisVo;
import com.wjf.github.commons.properties.CodeProperties;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.redisproducerclient.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/redis")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@PostMapping("/setKey")
	public ResultTemplate<Boolean> setKey(@RequestBody RedisVo redisVo) {
		return ResultTemplate.getSuccessResult(redisService.setKey(redisVo.getKey(), redisVo.getValue()));
	}

	@PostMapping("/setExKey")
	public ResultTemplate<Boolean> setExKey(@RequestParam("key") String key, @RequestParam("expire") int expire, @RequestParam("value") String value) {
		return ResultTemplate.getSuccessResult(redisService.setExKey(key, expire, value));
	}

	@PostMapping("/setKey/prefix")
	public ResultTemplate<Boolean> setPrefixKey(@RequestBody RedisVo redisVo) {
		System.out.println(redisVo);
		return ResultTemplate.getSuccessResult(redisService.setKey(redisVo.getK(), redisVo.getKey(), redisVo.getValue()));
	}

	@PostMapping("/setKey/nxex")
	public ResultTemplate<Boolean> setKey(@RequestParam String key, @RequestParam String value, @RequestParam String nx, @RequestParam String ex, @RequestParam long expire) {
		return ResultTemplate.getSuccessResult(redisService.setKey(key, value, nx, ex, expire));
	}

	@PostMapping("/setKey/prefix/nxex")
	public ResultTemplate<Boolean> setKey(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam String value, @RequestParam String nx, @RequestParam String ex) {
		return ResultTemplate.getSuccessResult(redisService.setKey(key, name, value, nx, ex));
	}

	@PostMapping("/setKey/prefix/enum/nxex")
	public ResultTemplate<Boolean> setKey(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam String value, @RequestParam NXXX nxxx, @RequestParam EXPX expx) {
		return ResultTemplate.getSuccessResult(redisService.setKey(key, name, value, nxxx, expx));
	}

	@PostMapping("/setNXKey")
	public ResultTemplate<Boolean> setNXKey(@RequestParam String key, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.setNXKey(key, value));
	}

	@PostMapping("/setNXKey/prefix")
	public ResultTemplate<Boolean> setNXKey(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.setNXKey(key, name, value));
	}

	@PostMapping("/setObject")
	public <T> ResultTemplate<Boolean> setObject(@RequestParam String key, @RequestParam T t) {
		return ResultTemplate.getSuccessResult(redisService.setObject(key, t));
	}

	@PostMapping("/setObject/expire")
	public <T> ResultTemplate<Boolean> setObject(@RequestParam String key, @RequestParam int expire, @RequestParam T t) {
		return ResultTemplate.getSuccessResult(redisService.setObject(key, expire, t));
	}

	@PostMapping("/setObject/prefix")
	public <T> ResultTemplate<Boolean> setObject(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam T t) {
		return ResultTemplate.getSuccessResult(redisService.setObject(key, name, t));
	}

	@PostMapping("/lock")
	public ResultTemplate<Boolean> lock(@RequestParam String key, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.lock(key, value));
	}

	@PostMapping("/lock/prefix")
	public ResultTemplate<Boolean> lock(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.lock(key, name, value));
	}

	@PostMapping("/unlock")
	public ResultTemplate<Boolean> unlock(@RequestParam String key, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.unlock(key, value));
	}

	@PostMapping("/unlock/prefix")
	public ResultTemplate<Boolean> unlock(@RequestParam BasePrefixKey key, @RequestParam String name, @RequestParam String value) {
		return ResultTemplate.getSuccessResult(redisService.unlock(key, name, value));
	}

	@PostMapping("/expire")
	public ResultTemplate<Boolean> expire(@RequestParam String key, @RequestParam int expire) {
		return ResultTemplate.getSuccessResult(redisService.expire(key, expire));
	}

	@PostMapping("/expire/prefix")
	public ResultTemplate<Boolean> expire(@RequestParam BasePrefixKey key, @RequestParam String name) {
		return ResultTemplate.getSuccessResult(redisService.expire(key, name));
	}

	public ResultTemplate defaultFallBack() {
		log.error("***redis服务挂掉啦！***");
		return ResultTemplate.getFailResult(CodeProperties.REDIS_SERVICE_ERROR, "redis服务失败！", false);
	}
}
