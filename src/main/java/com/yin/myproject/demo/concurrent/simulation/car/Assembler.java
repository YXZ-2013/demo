package com.yin.myproject.demo.concurrent.simulation.car;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Assembler implements Runnable {
	private CarQueue chassisQueue;
	private CarQueue finishingQueue;
	private Car car;
	private CyclicBarrier barrier = new CyclicBarrier(4);
	private RobotPool robotPool;

	public Car car() {
		return car;
	}

	public CyclicBarrier barrier() {
		return barrier;
	}

	public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
		super();
		this.chassisQueue = chassisQueue;
		this.finishingQueue = finishingQueue;
		this.robotPool = robotPool;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car = chassisQueue.take();
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelRobot.class, this);
				barrier.await();
				finishingQueue.put(car);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("组装结束");
	}
}
