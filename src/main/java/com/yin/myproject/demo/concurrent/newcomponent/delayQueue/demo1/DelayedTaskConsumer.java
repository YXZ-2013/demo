package com.yin.myproject.demo.concurrent.newcomponent.delayQueue.demo1;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		super();
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished DelayedTaskConsumer");
	}

}
