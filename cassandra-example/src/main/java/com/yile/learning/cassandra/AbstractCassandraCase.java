package com.yile.learning.cassandra;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

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

	public ByteBuffer toByteBuffer(String value) throws RuntimeException {
		try {
			return ByteBuffer.wrap(value.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public String toString(ByteBuffer buffer) throws RuntimeException {
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public abstract void before() throws RuntimeException;

	public abstract void after() throws RuntimeException;
}
