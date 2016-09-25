package com.yin.myproject.demo.concurrent.base.create;

public class Main {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		t1.start();
		
		Thread t2 = new Thread(new MyThread2());
		t2.start();
		
		
	}
}
