package com.yin.myproject.demo.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CreateClientExample {

	public static void main(String[] args) throws Exception {
		String connectionString = "127.0.0.1:2181";
		CuratorFramework client = createSimple(connectionString);
		client.start();
		client.close();
	}

	public static CuratorFramework createSimple(String connectString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
	}

	public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
			int connectionTimeoutMs, int sessionTimeoutMs) {
		return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
	}
}
