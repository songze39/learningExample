package com.yile.learning.biz.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yile.learning.biz.UserManagerBiz;
import com.yile.learning.model.UserInfo;
import com.yile.learning.model.UserRole;
import com.yile.learning.service.RoleService;
import com.yile.learning.service.UserService;

@Component
public class UserManagerBizImpl implements UserManagerBiz {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	public List<UserRole> findUserRolesByUserId(int userId) {
		return roleService.findUserRolesByUserId(userId);
	}

	public Set<String> findUserRoleCodeByUserId(int userId) {
		List<UserRole> userRoles = findUserRolesByUserId(userId);
		Set<String> roleCodes = new HashSet<String>();
		for (UserRole userRole : userRoles) {
			roleCodes.add(userRole.getRoleCode());
		}
		return roleCodes;
	}

	@Override
	public UserInfo getUserInfoByParams(String userName, String userPwd) {
		return userService.getUserInfoByParams(userName, userPwd);
	}

	@Override
	public UserInfo getUserInfoByLoginName(String userName) {
		return userService.getUserInfoByLoginName(userName);
	}
}
