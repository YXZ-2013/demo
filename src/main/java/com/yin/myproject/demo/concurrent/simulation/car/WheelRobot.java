package com.yin.myproject.demo.concurrent.simulation.car;

public class WheelRobot extends Robot {

	public WheelRobot(RobotPool pool) {
		super(pool);
	}
	
	protected void performService(){
		System.out.println(this + " installing wheels");
		assembler.car().addWheels();
	}

}
