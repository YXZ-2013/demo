package com.yin.myproject.demo.concurrent.base.executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolDemo {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		int size = new Random().nextInt(10);// 随机生成若干个线程
		for (int i = 0; i < size; i++) {
			service.execute(new WorkerThread("线程" + i));
		}
		TimeUnit.SECONDS.sleep(5);
		service.shutdown();
	}
}
