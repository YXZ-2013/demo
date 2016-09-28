package com.yin.myproject.demo.concurrent.simulation.restaurant;

public class Plate {
	private final Order order;
	private final Food food;

	public Plate(Order order, Food food) {
		super();
		this.order = order;
		this.food = food;
	}

	public Order getOrder() {
		return order;
	}

	public Food getFood() {
		return food;
	}

	@Override
	public String toString() {
		return food.toString();
	}
}
