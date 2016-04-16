package com.yile.learning.biz;

import java.util.Set;

import com.yile.learning.model.UserInfo;

public interface UserManagerBiz {
	public UserInfo getUserInfoByParams(String userName, String userPwd);

	public UserInfo getUserInfoByLoginName(String userName);
	
	public Set<String> findUserRoleCodeByUserId(int userId);

	
	
}
