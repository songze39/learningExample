package com.yile.learning.mapper;

import com.rabbitframework.jadb.annontations.Mapper;
import com.rabbitframework.jadb.annontations.Param;
import com.rabbitframework.jadb.annontations.Select;
import com.yile.learning.model.UserInfo;

@Mapper
public interface UserMapper {
	@Select("select * from user_info where user_name=#{userName} and user_pwd=#{userPwd}")
	public UserInfo getUserInfoByParams(@Param("userName") String userName, @Param("userPwd") String userPwd);

    @Select("select * from user_info where user_name=#{userName}")
	public UserInfo getUserInfoByLoginName(String userName);

}
