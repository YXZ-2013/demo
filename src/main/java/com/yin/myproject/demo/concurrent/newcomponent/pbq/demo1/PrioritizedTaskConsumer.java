package com.yin.myproject.demo.concurrent.newcomponent.pbq.demo1;

import java.util.concurrent.PriorityBlockingQueue;

public class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;

	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		super();
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}

}
