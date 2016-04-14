package com.yile.learning.security.realm;

import javax.annotation.Resource;

import com.rabbitframework.security.authc.*;
import com.rabbitframework.security.codec.ByteSource;
import com.rabbitframework.security.crypto.hash.Md5Hash;
import com.yile.learning.model.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitframework.security.authz.AuthorizationInfo;
import com.rabbitframework.security.realm.AuthorizingRealm;
import com.rabbitframework.security.subject.PrincipalCollection;
import com.yile.learning.service.UserService;

public class SecurityRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(SecurityRealm.class);

    /**
     * 授权认证,在配有缓存时只调用一次
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("AuthorizationInfo:" + getName());
        return null;
    }

    /**
     * 登录认证,登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("AuthenticationInfo:" + getName());
        UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToke.getPassword());
        String username = usernamePasswordToke.getUsername();
        logger.debug("====================doGetAuthenticationInfo begin ==========================");
        logger.debug("username: " + username);
        logger.debug("password: ");
        logger.debug(usernamePasswordToke.getPassword());
        logger.debug("principal: " + usernamePasswordToke.getPrincipal());
        logger.debug("======================doGetAuthenticationInfo end ========================");
        UserInfo userInfo = userService.getUserInfoByLoginName(username);
        if (userInfo == null) {
            throw new UnknownAccountException();//没有找到帐号
        }
        userInfo = userService.getUserInfoByParams(username, new Md5Hash(password).toString());
        if (userInfo == null) {
            throw new IncorrectCredentialsException();//用户名或密码不匹配
        }

        return new SimpleAuthenticationInfo(new SecurityUser(username, userInfo.getUserId(), username),
                password, getName());
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("123").toString());
    }

}
