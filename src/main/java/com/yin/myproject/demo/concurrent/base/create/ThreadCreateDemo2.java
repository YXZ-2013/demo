package com.yin.myproject.demo.concurrent.base.create;

class MyThread5 implements Runnable {

	private int ticket = 10;

	public MyThread5() {
	}

	public void run() {
		for (int i = 0; i < 500; i++) {
			if (this.ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
			}
		}
	}
}

public class ThreadCreateDemo2 {
	public static void main(String[] args) {
		MyThread5 mt = new MyThread5();
		Thread mt1 = new Thread(mt, "一号窗口");
		Thread mt2 = new Thread(mt, "二号窗口");
		Thread mt3 = new Thread(mt, "三号窗口");
		mt1.start();
		mt2.start();
		mt3.start();
	}
}