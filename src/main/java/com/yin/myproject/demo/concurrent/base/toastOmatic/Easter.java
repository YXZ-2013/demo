package com.yin.myproject.demo.concurrent.base.toastOmatic;

public class Easter implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;

	public Easter(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch (Exception e) {
			System.out.println("Easter interrupted");
		}
		System.out.println("Easter offf");
	}

}
