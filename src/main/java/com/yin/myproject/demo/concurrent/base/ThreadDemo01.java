package com.yin.myproject.demo.concurrent.base;

public class ThreadDemo01 extends Thread {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ThreadDemo01 demo01 = new ThreadDemo01();
			demo01.setName("name" + i);
			demo01.start();
		}
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
