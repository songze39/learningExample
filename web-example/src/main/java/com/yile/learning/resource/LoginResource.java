package com.yile.learning.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rabbitframework.security.SecurityUtils;
import com.rabbitframework.security.subject.Subject;

@Component("loginResource")
@Path("")
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
		return new Viewable("/login/login.jsp");
	}

	@POST
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	public String loginPost() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			logger.debug("postLogin" + currentUser);
		} else {
			logger.debug("nulllogin");
		}
		return "dddd";
	}

	@GET
	@Path("index")
	public Viewable index() {
		logger.debug("index");
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
