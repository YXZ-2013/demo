package com.yin.myproject.demo.concurrent.base.toastOmatic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count;
	private Random rand = new Random(47);

	public Toaster(ToastQueue toastQueue) {
		this.toastQueue = toastQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}

}
