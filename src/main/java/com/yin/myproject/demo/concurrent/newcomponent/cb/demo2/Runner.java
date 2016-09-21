package com.yin.myproject.demo.concurrent.newcomponent.cb.demo2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Runner implements Runnable {
	private CyclicBarrier barrier;

	private String name;

	public Runner(CyclicBarrier barrier, String name) {
		super();
		this.barrier = barrier;
		this.name = name;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2 * (new Random().nextInt(8)));
			System.out.println(name + " 准备好了。。。。");
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + " 起跑！");
	}

}
