package com.yin.myproject.demo.concurrent.base.restaurant;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}
				}
				if (++count == 10) {
					System.out.println("Out of food,closing");
					restaurant.service.shutdown();
				}
				System.out.println("Order up!");
				synchronized (restaurant.wp) {
					restaurant.meal = new Meal(count);
					restaurant.wp.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("Chef interrupted");
		}
	}
}
