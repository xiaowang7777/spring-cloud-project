package com.springcloud.userCenter.mapper;

import com.springcloud.userCenter.domain.UserLoginDo;
import com.springcloud.userCenter.vo.UserNormalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {

	/**
	 * 用户登录查询账户基本信息，根据账号、密码查询
	 * @param loginDo
	 * @return
	 */
	UserNormalInfo userLoginByAccount(UserLoginDo loginDo);

	/**
	 * 用户登陆查询账户信息，根据电话号码、密码查询
	 * @param loginDo
	 * @return
	 */
	UserNormalInfo userLoginByTelephoneNumber(UserLoginDo loginDo);


}
