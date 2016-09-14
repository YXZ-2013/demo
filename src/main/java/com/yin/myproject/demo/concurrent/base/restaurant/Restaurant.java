package com.yin.myproject.demo.concurrent.base.restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
	Meal meal;
	ExecutorService service = Executors.newCachedThreadPool();
	public Chef chef = new Chef(this);
	public WaitPerson wp = new WaitPerson(this);
	public Restaurant() {
		service.execute(chef);
		service.execute(wp);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
}
