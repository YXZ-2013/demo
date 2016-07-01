package com.yin.myproject.demo.pattern.decorator.ex2;

public class CustomerFilterInputStream implements CustomerInputStream{

	private CustomerInputStream ci;
	
	public CustomerFilterInputStream(CustomerInputStream ci) {
		super();
		this.ci = ci;
	}

	public void read() {
		ci.read();
	}

	public void close() {
		ci.close();
	}

}
