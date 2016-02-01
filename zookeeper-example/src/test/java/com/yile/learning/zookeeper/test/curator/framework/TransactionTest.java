package com.yile.learning.zookeeper.test.curator.framework;

import java.util.Collection;

import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.junit.Test;
import com.yile.learning.zookeeper.test.curator.CuratorAbstractTest;

/**
 * 事务操作
 * 
 */
public class TransactionTest extends CuratorAbstractTest {
	@Test
	public void ransaction() {
		try {
			// 开启事务
			CuratorTransaction transaction = curatorFramework.inTransaction();
			Collection<CuratorTransactionResult> results = transaction.create()
					.forPath("/a/path", "some data".getBytes())
					.and()
					.setData().forPath("/another/path", "other data".getBytes())
					.and().delete()
					.forPath("/yet/another/path").and()
					.commit();
			for (CuratorTransactionResult result : results) {
				System.out.println(result.getForPath() + " - " + result.getType());
			}
		} catch (Exception e) {
		}

	}
}