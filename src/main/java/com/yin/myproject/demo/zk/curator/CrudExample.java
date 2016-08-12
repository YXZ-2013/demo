package com.yin.myproject.demo.zk.curator;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

public class CrudExample {
	private static final String PATH = "/example/basic";

	public static void main(String[] args) throws Exception {
		CuratorFramework client = CreateClientExample.createSimple("127.0.0.1:2181");
		client.start();
		// create(client, "/example", "".getBytes());
		// create(client, PATH, "".getBytes());
		System.out.println(createEphmeralSequential(client, PATH, "123".getBytes()));
		System.out.println(createEphmeralSequential(client, PATH, "234".getBytes()));
		// client.close();
	}

	public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
		client.create().forPath(path, payload);
	}

	public static void delete(CuratorFramework client, String path) throws Exception {
		client.delete().forPath(path);
	}

	public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
		client.setData().forPath(path, payload);
	}

	public static void getData(CuratorFramework client, String path) throws Exception {
		client.getData().forPath(path);
	}

	public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
		client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
	}

	public static String createEphmeralSequential(CuratorFramework client, String path, byte[] payload)
			throws Exception {
		return client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
	}

	public void setDataAsync(CuratorFramework client, String path, String content) throws Exception {
		CuratorListener listener = new CuratorListener() {
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

			}
		};
		client.getCuratorListenable().addListener(listener);
		client.setData().inBackground().forPath(path, content.getBytes());
	}

	public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
		client.delete().guaranteed().forPath(path);
	}

	public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher)
			throws Exception {
		return client.getChildren().usingWatcher(watcher).forPath(path);
	}
}
