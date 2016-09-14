package com.yin.myproject.demo.concurrent.base.NotifyVsNotifyAll;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new Task());
		}
		service.execute(new Task2());
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			boolean prod = true;

			public void run() {
				if (prod) {
					System.out.println("notify()");
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.println("notifyAll()");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("Timer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task2.blocker.prodAll() ");
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Shutting down ");
		service.shutdown();
	}
}
