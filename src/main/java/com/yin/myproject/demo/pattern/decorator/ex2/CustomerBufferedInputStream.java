package com.yin.myproject.demo.pattern.decorator.ex2;

public class CustomerBufferedInputStream extends CustomerFilterInputStream{

	public CustomerBufferedInputStream(CustomerInputStream stream) {
		super(stream);
	}
	
	public void buffered(){
		System.out.println("来点缓存。。。。");
	}
	
	@Override
	public void read() {
		buffered();
		super.read();
	}
	
	@Override
	public void close() {
		super.close();
	}
}
