package com.yin.myproject.demo.proxy.dynamic2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

	private Object proxied;

	public ProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before do something ... ...");
		Object result = method.invoke(proxied, args);
		System.out.println("after do something ... ...");
		return result;
	}

	public static void main(String[] args) {
		RealSubject subject = new RealSubject();
		ProxyHandler handler = new ProxyHandler(subject);
		Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
				new Class[] { Subject.class }, handler);
		proxySubject.doSomething();
	}
}
