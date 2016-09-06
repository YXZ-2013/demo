package com.yin.myproject.demo.concurrent;

public class RunnableDemo01 implements Runnable {

	public static void main(String[] args) {
		RunnableDemo01 demo = new RunnableDemo01();
		Thread t = new Thread(demo);
		t.start();
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
