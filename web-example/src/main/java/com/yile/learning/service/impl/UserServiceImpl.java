package com.yile.learning.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yile.learning.mapper.UserMapper;
import com.yile.learning.model.UserInfo;
import com.yile.learning.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
//	@Resource
	private UserMapper userMapper;

	public UserInfo getUserInfo(String userName, String userPwd) {
		return userMapper.getUserInfo(userName, userPwd);
	}
}
