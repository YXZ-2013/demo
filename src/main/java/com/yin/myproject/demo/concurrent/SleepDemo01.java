package com.yin.myproject.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepDemo01 extends LiftOff{
	@Override
	public void run() {
		super.run();
		try {
			TimeUnit.MILLISECONDS.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			service.execute(new SleepDemo01());
		}
		service.shutdown();
	}
}
