package com.wjf.github.producerclient.service.impl;

import com.wjf.github.commons.domain.producer.UserInfo;
import com.wjf.github.producerclient.mapper.UserInfoMapper;
import com.wjf.github.producerclient.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo logIn(UserInfo userInfo) {
		log.info("***准备查询***");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userInfoMapper.logIn(userInfo);
	}
}
