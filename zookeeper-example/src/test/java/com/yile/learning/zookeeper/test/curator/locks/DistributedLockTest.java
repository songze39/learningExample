package com.yile.learning.zookeeper.test.curator.locks;

import java.util.List;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.yile.learning.zookeeper.test.curator.CuratorAbstractTest;

/**
 * 分布式锁实例
 * 
 */
public class DistributedLockTest extends CuratorAbstractTest {
	private static final String PATH = "/locks";

	// 进程内部（可重入）读写锁
	private InterProcessReadWriteLock lock;
	// 读锁
	private InterProcessLock readLock;
	// 写锁
	private InterProcessLock writeLock;

	@Before
	public void before() {
		super.before();
		lock = new InterProcessReadWriteLock(curatorFramework, PATH);
		readLock = lock.readLock();
		writeLock = lock.writeLock();
	}

	@Test
	public void distributedLock() {
		try {
			List<Thread> jobs = Lists.newArrayList();
			for (int i = 0; i < 10; i++) {
				Thread t = new Thread(new ParallelJob("Parallel任务" + i, readLock));
				jobs.add(t);
			}

			for (int i = 0; i < 10; i++) {
				Thread t = new Thread(new MutexJob("Mutex任务" + i, writeLock));
				jobs.add(t);
			}

			for (Thread t : jobs) {
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
