package com.yile.learning.mapper;

import java.util.List;

import com.rabbitframework.jadb.annontations.Mapper;
import com.rabbitframework.jadb.annontations.Select;
import com.yile.learning.model.UserRole;

@Mapper
public interface RoleMapper {
	@Select("select * from user_role where user_id=#{userId}")
	public List<UserRole> findUserRolesByUserId(int userId);
}
