package com.yin.myproject.demo.pattern.decorator.ex3;

public class DressUp implements Woman{
	
	private Woman woman;

	public DressUp(Woman woman) {
		super();
		this.woman = woman;
	}

	public void dressUp() {
		woman.dressUp();
	}
	
	
}
