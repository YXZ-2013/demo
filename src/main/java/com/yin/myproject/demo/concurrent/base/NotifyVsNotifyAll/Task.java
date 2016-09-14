package com.yin.myproject.demo.concurrent.base.NotifyVsNotifyAll;

public class Task implements Runnable {
	static Blocker blocker = new Blocker();

	public void run() {
		blocker.waitingCall();
	}

}
