package com.yile.learning.security.realm;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.rabbitframework.security.authc.AuthenticationException;
import com.rabbitframework.security.authc.AuthenticationInfo;
import com.rabbitframework.security.authc.AuthenticationToken;
import com.rabbitframework.security.authc.IncorrectCredentialsException;
import com.rabbitframework.security.authc.SimpleAuthenticationInfo;
import com.rabbitframework.security.authc.UnknownAccountException;
import com.rabbitframework.security.authc.UsernamePasswordToken;
import com.rabbitframework.security.authz.AuthorizationInfo;
import com.rabbitframework.security.authz.SimpleAuthorizationInfo;
import com.rabbitframework.security.crypto.hash.Md5Hash;
import com.rabbitframework.security.realm.AuthorizingRealm;
import com.rabbitframework.security.subject.PrincipalCollection;
import com.yile.learning.biz.UserManagerBiz;
import com.yile.learning.model.UserInfo;

public class SecurityRealm extends AuthorizingRealm {
    @Autowired
    private UserManagerBiz userManagerBiz;
    private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

    /**
     * 授权认证,在配有缓存时只调用一次
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.debug("AuthorizationInfo:" + getName());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object obj = principals.getPrimaryPrincipal();
        if (obj instanceof SecurityUser) {
            logger.debug("====================doGetAuthorizationInfo begin ==========================");
            SecurityUser securityUser = (SecurityUser) obj;
            int userId = securityUser.getUserId();
            Set<String> userRoles = userManagerBiz.findUserRoleCodeByUserId(userId);
            authorizationInfo.addRoles(userRoles);
            logger.debug("====================doGetAuthorizationInfo end ==========================");
        }
        return authorizationInfo;
    }

    /**
     * 登录认证,登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        logger.debug("AuthenticationInfo:" + getName());
        UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToke.getPassword());
        String username = usernamePasswordToke.getUsername();
        logger.debug("====================doGetAuthenticationInfo begin ==========================");
        logger.debug("username: " + username);
        logger.debug("password: " + password);
        logger.debug("principal: " + usernamePasswordToke.getPrincipal());
        logger.debug("======================doGetAuthenticationInfo end ========================");
        UserInfo userInfo = userManagerBiz.getUserInfoByLoginName(username);
        if (userInfo == null) {
            throw new UnknownAccountException();// 没有找到帐号
        }
        userInfo = userManagerBiz.getUserInfoByParams(username, new Md5Hash(
                password).toString());
        if (userInfo == null) {
            throw new IncorrectCredentialsException();// 用户名或密码不匹配
        }

        return new SimpleAuthenticationInfo(new SecurityUser(username,
                userInfo.getUserId(), username), password, getName());
    }
}
