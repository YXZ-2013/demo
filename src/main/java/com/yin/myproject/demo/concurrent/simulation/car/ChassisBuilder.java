package com.yin.myproject.demo.concurrent.simulation.car;

import java.util.concurrent.TimeUnit;

public class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;

	public ChassisBuilder(CarQueue carQueue) {
		super();
		this.carQueue = carQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Car c = new Car(counter++);
				System.out.println("ChassisBuilder created " + c);
				carQueue.put(c);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ChassisBuilder off");
	}

}
