package com.yin.myproject.demo.concurrent.base.blocked;

public class SynchronizedBlocked implements Runnable {

	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();
			};
		}.start();
	}

	public void run() {
		System.out.println("Trying to calling f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}

}
