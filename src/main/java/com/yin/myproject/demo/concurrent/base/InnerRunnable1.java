package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.TimeUnit;

public class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;
	public InnerRunnable1(String name){
		inner = new Inner(name);
	}

	private class Inner implements Runnable {
		Thread t;
		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}
	public static void main(String[] args) {
		new InnerRunnable1("innerRunnable1");
	}
}
