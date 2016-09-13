package com.yin.myproject.demo.concurrent.base.interrupt;

import java.util.Random;

public class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		// int temp = count;
		// if (rand.nextBoolean()) {
		// Thread.yield();
		// }
		// return (count = ++temp);
		return ++count;
	}

	public synchronized int value() {
		return count;
	}
}
