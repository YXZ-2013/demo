package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.TimeUnit;

public class InnerRunnable2 {

	private int countDown = 5;
	private Thread t;

	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {

			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						TimeUnit.SECONDS.sleep(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public String toString() {
				return Thread.currentThread().getName() + "： " + countDown;
			}
		},name);
		t.start();
	}
	
	public static void main(String[] args) {
		new InnerRunnable2("innerRunnable2");
	}
}
