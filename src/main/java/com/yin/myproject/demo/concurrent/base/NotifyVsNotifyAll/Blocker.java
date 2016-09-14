package com.yin.myproject.demo.concurrent.base.NotifyVsNotifyAll;

public class Blocker {
	synchronized void waitingCall() {
		try {
			while (!Thread.interrupted()) {
				wait();
				System.out.println(Thread.currentThread() + " ");
			}
		} catch (InterruptedException e) {

		}
	}

	synchronized void prod() {
		notify();
	}

	synchronized void prodAll() {
		notifyAll();
	}
}
