package com.yin.myproject.demo.concurrent.newcomponent.cdl.demo02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CacheHealthChecker extends BaseHealthChecker {

	public CacheHealthChecker(CountDownLatch _latch) {
		super(_latch, "Cache Service");
	}

	@Override
	public void verifyService() {
		System.out.println("Checking " + this.getServiceName());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}

}
