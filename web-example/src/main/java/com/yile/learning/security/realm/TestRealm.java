package com.yile.learning.security.realm;

import com.rabbitframework.security.authc.AuthenticationException;
import com.rabbitframework.security.authc.AuthenticationInfo;
import com.rabbitframework.security.authc.AuthenticationToken;
import com.rabbitframework.security.authz.AuthorizationInfo;
import com.rabbitframework.security.realm.AuthorizingRealm;
import com.rabbitframework.security.subject.PrincipalCollection;

public class TestRealm extends AuthorizingRealm {
	/**
	 * 授权认证,在配有缓存时只调用一次
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 登录认证,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return null;
	}

}
