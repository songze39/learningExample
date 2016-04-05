package com.yile.learning.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("loginResource")
@Path("")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginResource {
	/**
	 * 跳转到登录界面
	 * 
	 * @return
	 */
	@GET
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	public Viewable login() {
		return new Viewable("/login/login.jsp");
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
