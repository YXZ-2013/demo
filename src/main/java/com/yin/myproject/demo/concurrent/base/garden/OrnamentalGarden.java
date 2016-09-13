package com.yin.myproject.demo.concurrent.base.garden;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new Entrance(i));
		}
		TimeUnit.MILLISECONDS.sleep(45);
		Entrance.cancel();
		service.shutdown();
		if (!service.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrance: " + Entrance.sumEntrance());
	}
}
