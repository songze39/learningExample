package com.yile.learning.rest.test.user;

import com.yile.learning.model.UserInfo;
import com.yile.learning.rest.test.AbstractSpringTestCase;
import com.yile.learning.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends AbstractSpringTestCase {
    private static final Logger logger = LogManager.getLogger(UserServiceTest.class);
    @Autowired
    private UserService userService;

    @Test
    public void getUserInfo() {
        UserInfo userInfo = userService.getUserInfoByParams("test", "test");
        logger.info("name:"+ userInfo.getUserName()+","+userInfo.getUserPwd());
    }
}
