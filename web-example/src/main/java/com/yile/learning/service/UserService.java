package com.yile.learning.service;

import com.yile.learning.model.UserInfo;

public interface UserService {
	public UserInfo getUserInfo(String userName, String userPwd);
}
