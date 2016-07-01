package com.yin.myproject.demo.pattern.decorator.ex1;

/**
 * 定义装饰者
 *
 */
public class Decorator implements Human {
	
	private Human human;
	
	public Decorator(Human human) {
		super();
		this.human = human;
	}

	public void wearClothes() {
		human.wearClothes();
	}

	public void walkToWhere() {
		human.walkToWhere();
	}

}
