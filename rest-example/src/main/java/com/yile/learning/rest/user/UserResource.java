package com.yile.learning.rest.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yile.learning.rest.AbstractContextResource;

@Component("userResource")
@Path("")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserResource extends AbstractContextResource {

	@GET
	@Path("userName")
	@Produces(MediaType.TEXT_PLAIN)
	public String userName() {
		return "justin";
	}
}
