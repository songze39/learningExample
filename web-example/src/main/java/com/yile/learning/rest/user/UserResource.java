package com.yile.learning.rest.user;

import java.io.IOException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rabbitframework.web.resources.RabbitContextResource;
import com.yile.learning.rest.model.User;

@Component("userResource")
@Path("")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserResource extends RabbitContextResource {

	/**
	 * get请求
	 * 
	 * @return
	 */
	@GET
	@Path("getUserName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserName() {
		return "justin";
	}

	@GET
	@Path("testIndex")
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		return new Viewable("/index.jsp", null);
	}

	/**
	 * get请求,传递参数 example: /getUserNameById/test?id=1234
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("getUserNameById/{uriId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserNameById(@PathParam("uriId") String uriId,
			@QueryParam("id") @DefaultValue("description") String id) {
		System.out.println("uriId:" + uriId);
		System.out.println("getUserNameById:" + id);
		return id;
	}

	@PUT
	@Path("putId")
	@Produces(MediaType.TEXT_PLAIN)
	public String putId(String id) {
		System.out.println("putId:" + id);
		return "putSuccess";
	}

	@PUT
	@Path("putObject")
	@Produces(MediaType.TEXT_PLAIN)
	public String putObject(User user) {
		System.out.println("putId:" + user.getName());
		return "putObjectSuccess";
	}

	@POST
	@Path("post")
	@Produces(MediaType.TEXT_PLAIN)
	public String post(@BeanParam User user) {
		System.out.println("putId:" + user.getId());
		return "postSuccess";
	}
}
