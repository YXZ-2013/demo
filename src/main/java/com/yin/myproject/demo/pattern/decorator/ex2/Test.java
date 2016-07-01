package com.yin.myproject.demo.pattern.decorator.ex2;

public class Test {
	public static void main(String[] args) {
		CustomerInputStream stream = new CustomerBufferedInputStream(new CustomerFileInputStream());
		stream.read();
	}
}
