package com.yin.myproject.demo.proxy.Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookStoreProxy implements InvocationHandler {

	private Object target;

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		System.out.println("实现调用之前");
		result = method.invoke(target, args);
		System.out.println("实现调用之后");
		return result;
	}

	public static void main(String[] args) {
		BookStoreProxy proxy = new BookStoreProxy();
		BookStore store = (BookStore) proxy.bind(new BookStoreImpl());
		store.addBook();
	}
}
