package com.yile.learning.cassandra;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractCassandraCase {
	@Before
	public void caseBefore() {
		before();
	}

	@After
	public void caseAfter() {
		after();
	}

	public abstract void before() throws RuntimeException;

	public abstract void after() throws RuntimeException;
}
