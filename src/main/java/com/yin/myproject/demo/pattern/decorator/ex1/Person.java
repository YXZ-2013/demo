package com.yin.myproject.demo.pattern.decorator.ex1;

/**
 * 定义具体被装饰者 
 *
 */
public class Person implements Human{

	public void wearClothes() {
		System.out.println("穿什么呢。。");
	}

	public void walkToWhere() {
		System.out.println("去哪里呢。。");
	}
	
}
