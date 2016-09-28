package com.yin.myproject.demo.concurrent.simulation.car;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarBuilder {
	public static void main(String[] args) throws InterruptedException {
		CarQueue chassisQueue = new CarQueue();
		CarQueue finishingQueue = new CarQueue();
		ExecutorService service = Executors.newCachedThreadPool();
		RobotPool pool = new RobotPool();
		service.execute(new EngineRobot(pool));
		service.execute(new WheelRobot(pool));
		service.execute(new DriveTrainRobot(pool));
		service.execute(new Assembler(chassisQueue, finishingQueue, pool));
		service.execute(new Reporter(finishingQueue));
		service.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(7);
		service.shutdownNow();
	}
}	
