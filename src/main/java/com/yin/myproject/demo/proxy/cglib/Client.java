package com.yin.myproject.demo.proxy.cglib;

public class Client {
	public static void main(String[] args) {
		BookServiceBean service = BookServiceFactory.getInstance();
		doMethod(service);
	}

	private static void doMethod(BookServiceBean service) {
		service.create();
		service.query();
		service.update();
		service.delete();
	}
	
	
}
