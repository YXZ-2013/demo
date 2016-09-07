package com.yin.myproject.demo.concurrent.base;

public class YieldDemo01 {
	public static void main(String[] args) {
		YieldThread t1 = new YieldThread("线程一");
		YieldThread t2 = new YieldThread("线程二");
		t1.start();
		t2.start();
	}
}

class YieldThread extends Thread {
	private String name;

	public YieldThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(name + i);
			if (i == 5) {
				Thread.yield();
			}
		}
	}
}
