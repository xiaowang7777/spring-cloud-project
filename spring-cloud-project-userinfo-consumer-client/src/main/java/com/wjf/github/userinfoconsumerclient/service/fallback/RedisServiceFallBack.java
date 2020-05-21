package com.wjf.github.userinfoconsumerclient.service.fallback;

import com.wjf.github.commons.domain.redis.vo.RedisVo;
import com.wjf.github.commons.properties.CodeProperties;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.userinfoconsumerclient.service.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallBack implements RedisService {

	@Override
	public ResultTemplate<Boolean> setKey(RedisVo redisVo) {
		return ResultTemplate.getFailResult(CodeProperties.REDIS_SERVICE_ERROR, "redis请求超时！",false);
	}
}
