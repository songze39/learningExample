package com.yile.learning.rest.test.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.yile.learning.resource.UserResource;
import com.yile.learning.rest.test.AbstractSpringTestCase;

public class UserResourceTest extends AbstractSpringTestCase {
	private static final Logger logger = LogManager.getLogger(UserResourceTest.class);

	@Test
	public void TestGetUsername() {
		UserResource userResource = this.getBean(UserResource.class);
		logger.info("userName:" + userResource.getUserName());
	}
}
