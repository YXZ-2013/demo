package com.yin.myproject.demo.concurrent.simulation.bank;

/**
 * 客户
 *
 */
public class Customer {
	private final int serviceTime;// 服务时间

	public Customer(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	@Override
	public String toString() {
		return "[" + serviceTime + "]";
	}
}
