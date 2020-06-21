package com.wjf.github.springcloud.alibaba.nacos.consumer.sentinelException;

import com.wjf.github.commons.util.ResultTemplate;

public class BlockHandler {
	public ResultTemplate blockHandler(){
		return ResultTemplate.getFailResult("运行异常！BLOCK!");
	}

	public ResultTemplate fallBackHandler(){
		return ResultTemplate.getFailResult("运行异常！FALLBACK!");
	}

}
