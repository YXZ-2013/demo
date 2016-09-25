package com.yin.myproject.demo.concurrent.base.create;

class MyThread4 extends Thread {

	private int ticket = 10;
	private String name;

	public MyThread4(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i < 500; i++) {
			if (this.ticket > 0) {
				System.out.println(this.name + "卖票---->" + (this.ticket--));
			}
		}
	}
}

public class ThreadCreateDemo1 {
	public static void main(String[] args) {
		Thread mt1 = new MyThread4("一号窗口");
		Thread mt2 = new MyThread4("二号窗口");
		Thread mt3 = new MyThread4("三号窗口");
		mt1.start();
		mt2.start();
		mt3.start();
	}
}