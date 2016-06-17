package com.yin.myproject.demo.proxy.cglib;

public class BookServiceFactory {
	private static BookServiceBean service = new BookServiceBean();

	public BookServiceFactory() {
	}

	public static BookServiceBean getInstance() {
		return service;
	}
}
