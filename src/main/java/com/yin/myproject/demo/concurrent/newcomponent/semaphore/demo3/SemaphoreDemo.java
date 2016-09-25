package com.yin.myproject.demo.concurrent.newcomponent.semaphore.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	final static int SIZE = 25;

	public static void main(String[] args) throws InterruptedException {
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++) {
			service.execute(new CheckoutTask<Fat>(pool));
		}
		System.out.println("All CheckoutTasks created");
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < SIZE; i++) {
			Fat f = pool.checkOut();
			System.out.println(i + ": main() thread checked out");
			f.operation();
			list.add(f);
		}
		Future<?> blocked = service.submit(new Runnable() {

			public void run() {
				try {
					pool.checkOut();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);
		System.out.println("Checking in objects in " + list);
		for (Fat f : list) {
			pool.checkIn(f);
		}
		for (Fat f : list) {
			pool.checkIn(f);
		}
		service.shutdownNow();
	}
}
