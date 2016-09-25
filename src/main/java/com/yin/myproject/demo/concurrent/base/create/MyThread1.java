package com.yin.myproject.demo.concurrent.base.create;

public class MyThread1 extends Thread {

	@Override
	public void run() {
		System.out.println("通过继承Thread类创建一个线程");
	}
}
