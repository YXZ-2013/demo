package com.yin.myproject.demo.concurrent.base.restaurant;

public class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal [orderNum=" + orderNum + "]";
	}
}
