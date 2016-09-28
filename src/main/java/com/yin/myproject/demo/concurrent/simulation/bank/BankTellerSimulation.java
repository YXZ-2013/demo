package com.yin.myproject.demo.concurrent.simulation.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		service.execute(new CustomerGenerator(customers));
		service.execute(new TellerManager(service, customers, ADJUSTMENT_PERIOD));
		TimeUnit.SECONDS.sleep(5);
		service.shutdown();
	}
}
