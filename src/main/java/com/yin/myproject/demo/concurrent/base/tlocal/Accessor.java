package com.yin.myproject.demo.concurrent.base.tlocal;

import java.util.concurrent.TimeUnit;

public class Accessor implements Runnable {
	private final int id;

	public Accessor(int id) {
		this.id = id;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}

}
