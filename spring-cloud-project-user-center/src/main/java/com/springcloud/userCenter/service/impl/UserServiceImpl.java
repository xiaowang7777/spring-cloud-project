package com.springcloud.userCenter.service.impl;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcloud.userCenter.controller.mo.UserLoginModel;
import com.springcloud.userCenter.domain.UserLoginDo;
import com.springcloud.userCenter.mapper.RoleInfoMapper;
import com.springcloud.userCenter.mapper.UserInfoMapper;
import com.springcloud.userCenter.service.UserService;
import com.springcloud.userCenter.vo.RolePathInfo;
import com.springcloud.userCenter.vo.UserNormalInfo;
import com.wjf.github.commons.domain.redis.prefixkey.RoleInfoPrefixKey;
import com.wjf.github.commons.domain.redis.prefixkey.UserTokenPrefixKey;
import com.wjf.github.commons.service.RedisService;
import com.wjf.github.commons.util.MD5Util;
import com.wjf.github.commons.util.UserInfoDontFindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private RoleInfoMapper roleInfoMapper;

	@Autowired
	private RedisService redisService;

	@Override
	public UserNormalInfo login(UserLoginModel model) throws UserInfoDontFindException, JsonProcessingException {
		UserLoginDo loginDo = new UserLoginDo();
		loginDo.setTelephoneNumber(model.getUserName());
		loginDo.setUserAccount(model.getUserName());
		loginDo.setPassword(model.getPassword());
		model = null;
		UserNormalInfo userNormalInfo = userInfoMapper.userLoginByAccount(loginDo);
		if (userNormalInfo == null) {
			userNormalInfo = userInfoMapper.userLoginByTelephoneNumber(loginDo);
		}
		if (userNormalInfo == null) {
			throw new UserInfoDontFindException();
		}

		String md5Str = MD5Util.getMD5Str(IdUtil.fastUUID());
		String str = MD5Util.getMD5Str(userNormalInfo.getUserId());

		userNormalInfo.setUserToken(md5Str);
		List<RolePathInfo> pathInfos = roleInfoMapper.findUserRoleByUserId(userNormalInfo.getUserId());

		redisService.setObject(RoleInfoPrefixKey.getBasePrefixKey(), str, pathInfos);
		redisService.setKey(UserTokenPrefixKey.getUserTokenPrefixKey(60 * 1000 * 60 * 3), str, userNormalInfo.getUserToken());

		return userNormalInfo;
	}
}
