package com.springcloud.userCenter.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcloud.userCenter.controller.mo.UserLoginModel;
import com.springcloud.userCenter.service.UserService;
import com.springcloud.userCenter.vo.UserNormalInfo;
import com.wjf.github.commons.domain.redis.prefixkey.UserTokenPrefixKey;
import com.wjf.github.commons.service.RedisService;
import com.wjf.github.commons.util.MD5Util;
import com.wjf.github.commons.util.UserInfoDontFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	@SentinelResource(value = "/spring-cloud-project-user-center/user/login",
			exceptionsToIgnore = {UserInfoDontFindException.class})
	public UserNormalInfo login(@RequestBody @Validated UserLoginModel model) throws UserInfoDontFindException, JsonProcessingException {
		return userService.login(model);
	}

}
