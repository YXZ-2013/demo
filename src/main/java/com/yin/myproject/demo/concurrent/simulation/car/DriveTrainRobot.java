package com.yin.myproject.demo.concurrent.simulation.car;

public class DriveTrainRobot extends Robot{

	public DriveTrainRobot(RobotPool pool) {
		super(pool);
	}

	@Override
	protected void performService() {
		System.out.print(this + " installing DrivenTrain");
		assembler.car().addDriveTrain();
	}
	
	
	
}
