package com.yin.myproject.demo.concurrent.base.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorDemo {
	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		System.out.println("Current Time = " + new Date());
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			WorkerThread worker = new WorkerThread("do heavy processing");
			scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
		}
		Thread.sleep(30000);

		scheduledThreadPool.shutdown();
		while (!scheduledThreadPool.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}
}
