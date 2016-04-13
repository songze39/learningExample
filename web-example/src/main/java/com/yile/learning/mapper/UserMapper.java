package com.yile.learning.mapper;

import com.rabbitframework.jadb.annontations.Mapper;
import com.rabbitframework.jadb.annontations.Param;
import com.rabbitframework.jadb.annontations.Select;
import com.yile.learning.model.UserInfo;

@Mapper
public interface UserMapper {
	@Select("select * from user_info where user_name=#{userName} and user_pwd=#{userPwd}")
	public UserInfo getUserInfo(@Param("userName") String userName, @Param("userName") String userPwd);

}
