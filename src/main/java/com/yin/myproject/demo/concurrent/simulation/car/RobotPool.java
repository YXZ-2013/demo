package com.yin.myproject.demo.concurrent.simulation.car;

import java.util.HashSet;
import java.util.Set;

public class RobotPool {
	private Set<Robot> pool = new HashSet<Robot>();

	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}

	public synchronized void hire(Class<? extends Robot> robotType, Assembler b) throws InterruptedException {
		for (Robot r : pool) {
			if (r.getClass().equals(robotType)) {
				pool.remove(r);
				r.assignAssembler(b);
				r.engage();
				return;
			}
		}

		wait();
		hire(robotType, b);
	}

	public void release(Robot robot) {
		add(robot);
	}
}
