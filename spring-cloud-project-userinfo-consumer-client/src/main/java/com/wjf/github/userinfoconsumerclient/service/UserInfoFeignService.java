package com.wjf.github.userinfoconsumerclient.service;

import com.wjf.github.commons.domain.producer.UserInfo;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.userinfoconsumerclient.service.fallback.UserInfoFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "SPRING-CLOUD-PROJECT-PRODUCER-CLIENT",fallback = UserInfoFeignServiceFallBack.class)
public interface UserInfoFeignService {

	@PostMapping("/userInfo/logIn")
	ResultTemplate<UserInfo> logIn(@RequestBody UserInfo userInfo);

}
