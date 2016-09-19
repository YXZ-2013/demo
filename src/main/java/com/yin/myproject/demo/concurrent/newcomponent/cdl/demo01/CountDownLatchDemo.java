package com.yin.myproject.demo.concurrent.newcomponent.cdl.demo01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	static final int SIZE = 100;

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++)
			service.execute(new WaitingTask(latch));
		for (int i = 0; i < SIZE; i++)
			service.execute(new TaskPortion(latch));
		System.out.println("Launched all tasks");
		service.shutdown();
	}
}