package com.yin.myproject.demo.concurrent.base.blokedqueue;

import java.util.concurrent.BlockingQueue;

import com.yin.myproject.demo.concurrent.base.LiftOff;

public class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		this.rockets = rockets;
	}

	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("interrupted during put()");
		}
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket;
				rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting LiftOffRunner");
		}
	}

}
