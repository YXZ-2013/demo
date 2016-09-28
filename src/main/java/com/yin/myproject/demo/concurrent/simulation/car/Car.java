package com.yin.myproject.demo.concurrent.simulation.car;

public class Car {
	private final int id;
	private boolean engine = false;
	private boolean driveTrain = false;
	private boolean wheels = false;

	public Car(int id) {
		super();
		this.id = id;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void addEngine() {
		engine = true;
	}

	public synchronized void addDriveTrain() {
		driveTrain = true;
	}

	public synchronized void addWheels() {
		wheels = true;
	}

	public synchronized String toString() {
		return "Car " + id + " [" + " engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels + " ]";
	}
}
