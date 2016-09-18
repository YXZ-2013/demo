package com.yin.myproject.demo.concurrent.base.waxOMatic2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.yin.myproject.demo.concurrent.base.waxomatic.Car;
import com.yin.myproject.demo.concurrent.base.waxomatic.WaxOff;
import com.yin.myproject.demo.concurrent.base.waxomatic.WaxOn;

public class WaxOmatic2 {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new WaxOff(car));
		service.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		service.shutdown();
	}
}
