package com.wjf.github.userinfoconsumerclient.service.fallback;

import com.wjf.github.commons.domain.producer.UserInfo;
import com.wjf.github.commons.util.ResultTemplate;
import com.wjf.github.userinfoconsumerclient.service.UserInfoFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserInfoFeignServiceFallBack implements UserInfoFeignService {
	@Override
	public ResultTemplate<UserInfo> logIn(UserInfo userInfo) {
		log.info("***登录接口请求报错:" + userInfo.toString() + "***");
		return ResultTemplate.getFailResult(5001, userInfo);
	}
}
