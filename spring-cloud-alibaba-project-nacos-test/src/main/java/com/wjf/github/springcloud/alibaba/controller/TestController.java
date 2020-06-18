package com.wjf.github.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wjf.github.commons.util.ResultTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/testD")
	@SentinelResource(value = "testD", fallback = "getTestError", blockHandler = "blockHandler")
	public ResultTemplate getTest() {
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("请求进来啦！");
		return ResultTemplate.getSuccessResult("success");
	}

	public ResultTemplate getBlockHandler() {
		return ResultTemplate.getFailResult("blockHandler");
	}

	public ResultTemplate getTestError() {
		return ResultTemplate.getFailResult("fallback");
	}

}
