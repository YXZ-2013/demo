package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}                                                                                                       

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			service.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started!");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
