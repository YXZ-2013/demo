package com.yin.myproject.demo.concurrent.base.waxomatic;

public class Car {
	// 表示涂腊-抛光的状态
	private boolean waxOn = false;

	// 已涂腊
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}

	// 已抛光
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	// 等待涂腊
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();
		}
	}

	// 等待抛光
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();
		}
	}
}
