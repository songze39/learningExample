package com.yile.learning.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.rabbitframework.security.authc.UsernamePasswordToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rabbitframework.security.SecurityUtils;
import com.rabbitframework.security.subject.Subject;
import com.yile.learning.security.realm.SecurityUser;

@Component("loginResource")
@Path("/")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginResource {
	private static final Logger logger = LogManager.getLogger(LoginResource.class);

	/**
	 * 跳转到登录界面
	 *
	 * @return
	 */
	@GET
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	public Viewable login() {
		logger.debug("getLogin");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new Viewable("/index.jsp");
		}
		return new Viewable("/login/login.jsp");
	}

	@POST
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	public Viewable loginPost(@FormParam("userName") String userName, @FormParam("password") String password) {
		logger.debug("loginPost");
		logger.debug("userName:" + userName);
		logger.debug("password:" + password);
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(false);
		// subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (Exception e) {
			logger.error("登陆失败!");
		}
		// 验证是否成功登录的方法
		if (subject.isAuthenticated()) {
			logger.error("登陆成功!");
			return new Viewable("/index.jsp");
		}
		return new Viewable("/login/login.jsp");
	}

	@GET
	@Path("index")
	public Viewable index() {
		logger.debug("index");
		Subject subject = SecurityUtils.getSubject();
		Object obj = subject.getPrincipal();
		SecurityUser securityUser = (SecurityUser) obj;
		logger.debug("userId:" + securityUser.getUserId());
		return new Viewable("/index.jsp");
	}

	/**
	 * 没有权限跳转到错误界面
	 *
	 * @return
	 */
	@GET
	@Path("unauthorized")
	@Produces(MediaType.TEXT_HTML)
	public Viewable unauthorized() {
		return new Viewable("/error/401.jsp");
	}
}
