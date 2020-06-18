package com.wjf.github.springcloud.alibaba.nacos.consumer.service.fallback;

import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.springcloud.alibaba.nacos.consumer.service.TestService;
import org.springframework.stereotype.Component;

@Component
public class AllFallback implements TestService {

	public ResultTemplate getTest(){
		return ResultTemplate.getFailResult("fegin调用超时！");
	}

}
