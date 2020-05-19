package com.wjf.github.producerclient.mapper;

import com.wjf.github.commons.domain.producer.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {
	UserInfo logIn(UserInfo userInfo);
}
