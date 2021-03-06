package com.yile.learning.service;

import java.util.List;

import com.yile.learning.model.UserInfo;
import com.yile.learning.model.UserRole;

public interface UserService {
	public UserInfo getUserInfoByParams(String userName, String userPwd);

	public UserInfo getUserInfoByLoginName(String userName);

}
