package com.yin.myproject.demo.concurrent.base.create;

public class MyThread2 implements Runnable {

	public void run() {
		System.out.println("通过实现Runnable接口创建一个线程");
	}
}
