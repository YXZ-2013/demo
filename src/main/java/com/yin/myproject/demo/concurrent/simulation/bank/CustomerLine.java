package com.yin.myproject.demo.concurrent.simulation.bank;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomerLine extends ArrayBlockingQueue<Customer> {

	private static final long serialVersionUID = 1L;

	public CustomerLine(int capacity) {
		super(capacity);
	}

	@Override
	public String toString() {
		if (this.size() == 0) {
			return "[Empty]";
		}
		StringBuilder sb = new StringBuilder();
		for (Customer customer : this) {
			sb.append(customer);
		}
		return sb.toString();
	}
}
