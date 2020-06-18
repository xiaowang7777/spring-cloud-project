package com.wjf.github.springcloud.alibaba.nacos.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.commons.util.ThreadUtil;
import com.wjf.github.springcloud.alibaba.nacos.consumer.sentinelException.BlockHandler;
import com.wjf.github.springcloud.alibaba.nacos.consumer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

@Slf4j
@RequestMapping("/test")
@Controller
@ResponseBody
public class TestController {

	@Value("${server.port}")
	private Integer port;

	private int i = 0;

	@Autowired
	private TestService testService;

	private final ThreadPoolExecutor executor;

	public TestController(AsyncConfigurer asyncConfigurer) {
		executor = (ThreadPoolExecutor) asyncConfigurer.getAsyncExecutor();
	}

	@GetMapping("/getTest")
	@SentinelResource(value = "Test", blockHandlerClass = {BlockHandler.class},
			blockHandler = "blockHandler", fallbackClass = {BlockHandler.class},
			fallback = "fallBackHandler")
	public ResultTemplate getTest() {
		log.info("请求来啦！{}", i++);
//		final AtomicInteger atomicInteger = new AtomicInteger(10);
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		executor.submit(new ThreadUtil(atomicInteger, Thread.currentThread()));
//		int i = atomicInteger.get();
//		for (; ; ) {
//			if (i == 0 || (i = atomicInteger.get()) == 0) {
//				break;
//			} else {
//				LockSupport.park();
//			}
//		}
		return ResultTemplate.getSuccessResult();
//		return testService.getTest();
	}

}
