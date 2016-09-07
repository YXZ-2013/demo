package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableDemo02 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		executor.submit(futureTask);
		executor.shutdown();

		Thread.sleep(1000);

		System.out.println("主线程在执行任务");
		System.out.println("task运行结果" + futureTask.get());
		System.out.println("所有任务执行完毕");
	}
}

class Task implements Callable<Integer> {
	public Integer call() throws Exception {
		System.out.println("子线程正在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += i;
		}
		return sum;
	}
}
