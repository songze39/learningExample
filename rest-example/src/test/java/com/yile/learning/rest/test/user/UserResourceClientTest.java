package com.yile.learning.rest.test.user;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class UserResourceClientTest {
	private static final String serverUri = "http://localhost:8080/lollipop-rest";

	@Test
	public void testUserName() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serverUri + "/userName");
		String str = webTarget.request().get(String.class);
		System.out.println("str:" + str);
	}
}
