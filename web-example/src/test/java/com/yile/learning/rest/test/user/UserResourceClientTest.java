package com.yile.learning.rest.test.user;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.yile.learning.rest.model.User;

public class UserResourceClientTest {
	private static final String serverUri = "http://localhost:8080/rest-example";

	 @Test
	public void testGetUserName() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/getUserName");
		String str = webTarget.request().get(String.class);
		System.out.println("str:" + str);
	}

	@Test
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
	public void testPutObject() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/putObject");
		User user = new User();
		user.setId("userId");
		user.setName("Objectparam");
		Entity<User> entity = Entity.entity(user, MediaType.APPLICATION_JSON);
		// 或
		// String input = "{\"id\":\"2\",\"name\":\"jsonstring\"}";
		// Entity<String> entity = Entity.entity(input,
		// MediaType.APPLICATION_JSON);
		String returnValue = webTarget.request().put(entity, String.class);
		System.out.println("returnValue:" + returnValue);
	}

	/**
	 * form表单提交，如果服务端接收参数没有使用@BeanParam或@FormParam的话，完全可以像put请求一样传参数
	 */
	 @Test
	public void testPost() {
		Client client = ClientBuilder.newClient();
		MultivaluedMap<String, String> multivaluedMap = new MultivaluedHashMap<>();
		multivaluedMap.putSingle("id", "userId");
		multivaluedMap.putSingle("name", "Objectparam");
		Form form = new Form(multivaluedMap);
		Entity<Form> entity = Entity.form(form);
		WebTarget webTarget = client.target(serverUri + "/post");
		String returnValue = webTarget.request().post(entity, String.class);
		System.out.println("returnValue:" + returnValue);
	}
}
