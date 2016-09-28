package com.yin.myproject.demo.concurrent.simulation.car;

import java.util.concurrent.BrokenBarrierException;

public abstract class Robot implements Runnable {
	private RobotPool pool;

	public Robot(RobotPool pool) {
		super();
		this.pool = pool;
	}

	protected Assembler assembler;

	public Robot assignAssembler(Assembler assembler) {
		this.assembler = assembler;
		return this;
	}

	private boolean engage = false;

	public synchronized void engage() {
		engage = true;
		notifyAll();
	}

	abstract protected void performService();

	public void run() {
		try {
			powerDown();
			while (!Thread.interrupted()) {
				performService();
				assembler.barrier().await();
				powerDown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(this + " off");
	}

	private synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null;
		pool.release(this);
		while (engage == false) {
			wait();
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
}
