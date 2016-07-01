package com.yin.myproject.demo.pattern.decorator.ex3;

public class Test {
	public static void main(String[] args) {
		Woman woman = new Thrush(new DressUp(new ProgrammerWoman()));
		woman.dressUp();
	}
}
