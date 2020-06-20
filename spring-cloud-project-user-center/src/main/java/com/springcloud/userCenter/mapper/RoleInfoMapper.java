package com.springcloud.userCenter.mapper;

import com.springcloud.userCenter.vo.RolePathInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
@Repository
public interface RoleInfoMapper {

	List<RolePathInfo> findUserRoleByUserId(@Validated @NotNull String userId);

}
