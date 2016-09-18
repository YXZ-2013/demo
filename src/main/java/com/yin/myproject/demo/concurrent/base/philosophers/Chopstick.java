package com.yin.myproject.demo.concurrent.base.philosophers;

public class Chopstick {
	private boolean taken = false;

	public synchronized void taken() throws InterruptedException {
		while (taken)
			wait();
		taken = true;
	}

	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
