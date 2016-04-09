package com.yile.learning.rest.test.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.yile.learning.resource.LearningResource;
import com.yile.learning.rest.test.AbstractSpringTestCase;

public class LearningResourceTest extends AbstractSpringTestCase {
	private static final Logger logger = LogManager.getLogger(LearningResourceTest.class);

	@Test
	public void TestGetUsername() {
		LearningResource userResource = this.getBean(LearningResource.class);
		logger.info("userName:" + userResource.getUserName());
	}
}
