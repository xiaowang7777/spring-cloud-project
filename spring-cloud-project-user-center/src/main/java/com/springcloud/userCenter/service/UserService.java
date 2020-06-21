package com.springcloud.userCenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcloud.userCenter.controller.mo.UserLoginModel;
import com.springcloud.userCenter.vo.UserNormalInfo;
import com.wjf.github.commons.util.UserInfoDontFindException;

public interface UserService {

	UserNormalInfo login(UserLoginModel model) throws UserInfoDontFindException, JsonProcessingException;

}
