package com.yile.learning.service;

import com.yile.learning.model.UserInfo;

public interface UserService {
    public UserInfo getUserInfoByParams(String userName, String userPwd);

    public UserInfo getUserInfoByLoginName(String userName);
}
