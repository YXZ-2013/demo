package com.yin.myproject.demo.concurrent.newcomponent.cdl.demo02;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker
{
	public DatabaseHealthChecker (CountDownLatch latch)
	{
		super(latch, "Database Service");
	}
	
	@Override
	public void verifyService() 
	{
		System.out.println("Checking " + this.getServiceName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}
}
