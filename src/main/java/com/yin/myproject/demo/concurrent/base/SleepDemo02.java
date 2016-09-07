package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thinking In Java 第21章 并发 练习6
 * 创建一个任务,它将睡眠1至10秒之间的随机数量的时间,然后显示它的睡眠时间并退出.创建并运行一定数量的这种任务
 * 
 * @author XunzhiYin
 */
public class SleepDemo02 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new SleepTask());
		}
		service.shutdown();
	}
}

class SleepTask implements Runnable {

	public void run() {
		int sleppTime = (int) (1 + Math.random() * (10 - 1));
		try {
			TimeUnit.SECONDS.sleep(sleppTime);
			System.out.println(Thread.currentThread().getName() + "休眠" + sleppTime + "s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
