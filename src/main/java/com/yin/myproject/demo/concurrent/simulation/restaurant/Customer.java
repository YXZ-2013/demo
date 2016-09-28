package com.yin.myproject.demo.concurrent.simulation.restaurant;

import java.util.concurrent.SynchronousQueue;

public class Customer implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>();
	public Customer(WaitPerson waitPerson) {
		super();
		this.waitPerson = waitPerson;
	}
	
	public void deliver(Plate p) throws InterruptedException{
		placeSetting.put(p);
	}
	
	public void run() {
	}
	
}
