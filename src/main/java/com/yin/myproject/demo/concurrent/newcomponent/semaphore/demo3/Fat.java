package com.yin.myproject.demo.concurrent.newcomponent.semaphore.demo3;

public class Fat {
	private volatile double d;
	private static int counter = 0;
	private final int id = counter++;

	public Fat(double d) {
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}

	public void operation() {
		System.out.println(this);
	}

	public String toString() {
		return "Fat id: " + id;
	}

}
