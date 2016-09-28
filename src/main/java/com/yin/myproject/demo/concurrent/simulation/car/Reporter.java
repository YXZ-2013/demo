package com.yin.myproject.demo.concurrent.simulation.car;

public class Reporter implements Runnable {
	private CarQueue carQueue;

	public Reporter(CarQueue carQueue) {
		super();
		this.carQueue = carQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(carQueue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Reporter off");
	}

}
