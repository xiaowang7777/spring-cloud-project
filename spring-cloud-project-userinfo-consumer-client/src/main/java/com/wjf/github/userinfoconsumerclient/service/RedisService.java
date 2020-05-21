package com.wjf.github.userinfoconsumerclient.service;

import com.wjf.github.commons.domain.redis.vo.RedisVo;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.userinfoconsumerclient.service.fallback.RedisServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "SPRING-CLOUD-PROJECT-REDIS-PRODUCER-CLIENT", fallback = RedisServiceFallBack.class)
public interface RedisService {
	@PostMapping("/redis/setKey/prefix")
	ResultTemplate<Boolean> setKey(@RequestBody RedisVo redisVo);
}
