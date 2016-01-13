package com.yile.learning.rest.test.user;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;
import org.junit.Test;

import com.yile.learning.rest.model.User;

public class UserResourceClientTest {
	private static final String serverUri = "http://localhost:8080/rest-example";

	// @Test
	public void testGetUserName() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/getUserName");
		String str = webTarget.request().get(String.class);
		System.out.println("str:" + str);
	}

	// @Test
	public void testPutId() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/putId");
		Entity<String> entity = Entity.text("testPutId");
		String returnValue = webTarget.request().put(entity, String.class);
		System.out.println("returnValue:" + returnValue);
	}

	/**
	 * 参数的传递可能是对象+json的MediaType类型也可以是string的json串
	 */
	@Test
	public void testObject() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/putObject");
		User user = new User();
		user.setId("userId");
		user.setName("Objectparam");
		Entity<User> entity = Entity.entity(user, MediaType.APPLICATION_JSON);
		// String input = "{\"id\":\"2\",\"name\":\"jsonstring\"}";
		// Entity<String> entity = Entity.entity(input,
		// MediaType.APPLICATION_JSON);
		String returnValue = webTarget.request().put(entity, String.class);
		System.out.println("returnValue:" + returnValue);
	}
}
