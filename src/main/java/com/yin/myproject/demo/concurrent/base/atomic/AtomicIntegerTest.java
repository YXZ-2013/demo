package com.yin.myproject.demo.concurrent.base.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger();

	public int getValue() {
		return i.get();
	}

	public void evenIncrement() {
		i.addAndGet(2);
	}

	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);

		ExecutorService service = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		service.execute(ait);
		while (true) {
			int val = ait.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
