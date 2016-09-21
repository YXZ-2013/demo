package com.yin.myproject.demo.concurrent.newcomponent.cb.demo3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Player implements Runnable {

	private final String name;
	private final CyclicBarrier barrier;

	public Player(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
			System.out.println(name + "已准备,等待其他玩家准备...");
			barrier.await(200, TimeUnit.MILLISECONDS);
			// barrier.await();
			TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
			System.out.println(name + "已加入游戏");
		} catch (InterruptedException e) {
			System.out.println(name + "离开游戏");
		} catch (BrokenBarrierException e) {
			System.out.println(name + "离开游戏");
		} catch (TimeoutException e) {
			System.out.println(name + "连接超时");
		}
	}
}