package com.yin.myproject.demo.concurrent.simulation.bank;

import java.util.concurrent.TimeUnit;

/**
 * 收纳员
 * 
 * @author XunzhiYin
 *
 */
public class Teller implements Runnable, Comparable<Teller> {
	private static int counter = 0;
	private final int id = counter++;
	private int customerServed = 0;
	private CustomerLine customers;
	private boolean servingCustomerLine = true;

	public Teller(CustomerLine customers) {
		super();
		this.customers = customers;
	}

	public int compareTo(Teller other) {
		return customerServed < other.customerServed ? -1 : (customerServed == other.customerServed ? 0 : 1);
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 从阻塞队列中获取顾客
				Customer customer = customers.take();
				// 正在服务于顾客
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customerServed++;
					while (!servingCustomerLine)
						wait();
				}
			}
		} catch (InterruptedException e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}

	/**
	 * 做其他事情
	 */
	public synchronized void doSomethingElse() {
		customerServed = 0;
		servingCustomerLine = false;
	}

	/**
	 * 服务于顾客
	 */
	public synchronized void serveCustomerLine() {
		assert !servingCustomerLine : "already serving: " + this;
		servingCustomerLine = true;
		notifyAll();
	}

	@Override
	public String toString() {
		return "Teller " + id + " ";
	}

	public String shortString() {
		return "T" + id;
	}

}
