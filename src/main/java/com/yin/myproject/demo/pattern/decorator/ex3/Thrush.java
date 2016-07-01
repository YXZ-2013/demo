package com.yin.myproject.demo.pattern.decorator.ex3;

public class Thrush extends DressUp{
	
	public Thrush(Woman woman) {
		super(woman);
	}
	
	@Override
	public void dressUp() {
		super.dressUp();
		System.out.println("画眉了，漂亮了一些。");
	}
}
