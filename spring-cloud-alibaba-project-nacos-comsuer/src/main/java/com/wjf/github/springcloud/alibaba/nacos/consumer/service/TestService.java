package com.wjf.github.springcloud.alibaba.nacos.consumer.service;


import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.springcloud.alibaba.nacos.consumer.service.fallback.AllFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "spring-cloud-alibaba-project-nacos-test",fallback = AllFallback.class)
public interface TestService {
	@GetMapping("/test/testD")
	ResultTemplate getTest() ;
}
