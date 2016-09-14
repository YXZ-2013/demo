package com.yin.myproject.demo.concurrent.base.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOmatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new WaxOff(car));
		service.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		service.shutdown();
	}
}
