package com.yin.myproject.demo.concurrent.simulation.car;

public class EngineRobot extends Robot {

	public EngineRobot(RobotPool pool) {
		super(pool);
	}

	@Override
	protected void performService() {
		System.out.print(this + " installing engine");
		assembler.car().addEngine();
	}

}
