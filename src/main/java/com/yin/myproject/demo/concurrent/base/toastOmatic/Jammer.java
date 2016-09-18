package com.yin.myproject.demo.concurrent.base.toastOmatic;

public class Jammer implements Runnable {
	private ToastQueue butteredQueue, fininshedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue fininshedQueue) {
		this.butteredQueue = butteredQueue;
		this.fininshedQueue = fininshedQueue;
	}

	public void run() {
		try {
			while(!Thread.interrupted()){
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				fininshedQueue.put(t);
			}
		} catch (Exception e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer off");
	}

}
