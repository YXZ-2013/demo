package com.yin.myproject.demo.zk.curator;

import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;

public class TransactionExample {
	public static void main(String[] args) {

	}

	public static Collection<CuratorTransactionResult> transaction(CuratorFramework client) throws Exception {
		Collection<CuratorTransactionResult> results = client.inTransaction().create()
				.forPath("/a/path", "some data".getBytes()).and().setData()
				.forPath("/another/path", "other data".getBytes()).and().setData()
				.forPath("/another/path", "other data".getBytes()).and().delete().forPath("/yet/another/path").and()
				.commit();
		for (CuratorTransactionResult result : results) {
			System.out.println(result.getForPath() + "-" + result.getType());
		}

		return results;
	}

	public static CuratorTransactionFinal addCreateToTransaction(CuratorTransaction transaction) throws Exception {
		return transaction.create().forPath("/a/path", "some data".getBytes()).and();
	}

	public static CuratorTransactionFinal addDeleteToTransaction(CuratorTransaction transaction) throws Exception {
		return transaction.delete().forPath("/another/path").and();
	}

	public static void commitTransaction(CuratorTransactionFinal transaction) throws Exception {
		transaction.commit();
	}
}
