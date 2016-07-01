package com.yin.myproject.demo.pattern.decorator.ex1;

public class Test {
	public static void main(String[] args) {
		Human person = new Person();
		Decorator decorator = new Decorator_two(new Decorator_one(new Decorator_zero(person)));
		decorator.wearClothes();
		decorator.walkToWhere();
	}
}
