package com.yin.myproject.demo.concurrent.simulation.bank;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 顾客生产者
 * @author dasd
 *
 */
public class CustomerGenerator implements Runnable {
	private CustomerLine customers;
	private static Random rand = new Random(47);

	public CustomerGenerator(CustomerLine customers) {
		super();
		this.customers = customers;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerGenerator terminating");
	}

}
