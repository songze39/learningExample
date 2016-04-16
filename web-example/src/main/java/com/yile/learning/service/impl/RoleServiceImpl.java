package com.yile.learning.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yile.learning.mapper.RoleMapper;
import com.yile.learning.model.UserRole;
import com.yile.learning.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper RoleMapper;

	@Override
	public List<UserRole> findUserRolesByUserId(int userId) {
		return RoleMapper.findUserRolesByUserId(userId);
	}

}
