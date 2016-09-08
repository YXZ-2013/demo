package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.TimeUnit;

public class InnerThread1 {
	private int countDown = 5;
	private Inner inner;

	public InnerThread1(String name) {
		inner = new Inner(name);
	}

	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0)
						return;
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		public String toString() {
			return getName() + " " + countDown;
		}
	}

	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
	}
}
