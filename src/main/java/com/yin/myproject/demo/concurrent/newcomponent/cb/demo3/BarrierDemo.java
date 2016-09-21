package com.yin.myproject.demo.concurrent.newcomponent.cb.demo3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		final CyclicBarrier barrier = new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			service.execute(new Player("玩家" + i, barrier));
		}
		service.shutdown();
	}
}
