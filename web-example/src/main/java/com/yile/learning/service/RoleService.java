package com.yile.learning.service;

import java.util.List;

import com.yile.learning.model.UserRole;

public interface RoleService {
	 public List<UserRole> findUserRolesByUserId(int userId);
}
