package com.yin.myproject.demo.concurrent.newcomponent.cb.demo3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BarrierDemo2 {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			public void run() {
				System.out.println("所有线程已到达栅栏点");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		for (int i = 0; i < 5; i++) {
			service.execute(new Player("玩家" + i, barrier));
		}
		service.shutdown();
	}
}
