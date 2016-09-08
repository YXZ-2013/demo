package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.TimeUnit;

public class InnerThread2 {
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}

	public static void main(String[] args) {
		new InnerThread2("innerThread2");
	}
}
