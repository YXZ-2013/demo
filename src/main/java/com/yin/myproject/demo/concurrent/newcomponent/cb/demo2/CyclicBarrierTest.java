package com.yin.myproject.demo.concurrent.newcomponent.cb.demo2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		// 如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
		// Waits until all parties have invoked await on this barrier.
		CyclicBarrier barrier = new CyclicBarrier(3);

		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(new Runner(barrier, "1号选手"));
		executor.execute(new Runner(barrier, "2号选手"));
		executor.execute(new Runner(barrier, "3号选手"));

		executor.shutdown();
	}
}
