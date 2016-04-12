package com.yile.learning.security.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitframework.security.authc.AuthenticationException;
import com.rabbitframework.security.authc.AuthenticationInfo;
import com.rabbitframework.security.authc.AuthenticationToken;
import com.rabbitframework.security.authz.AuthorizationInfo;
import com.rabbitframework.security.realm.AuthorizingRealm;
import com.rabbitframework.security.subject.PrincipalCollection;

public class SecurityRealm extends AuthorizingRealm {
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
		return null;
	}

}
