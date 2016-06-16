package com.yin.myproject.demo.proxy.dynamic3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler{

	private Object proxied;
	
	public ProxyHandler(Object proxied) {
		super();
		this.proxied = proxied;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before create method");
		Object result = method.invoke(proxied, args);
		System.out.println(args.toString());
		System.out.println("after create method");
		return result;
	}
	
	
	public static void main(String[] args) {
		RoleServiceImpl roleServiceImpl = new RoleServiceImpl();
		ProxyHandler handler = new ProxyHandler(roleServiceImpl);
		RoleService service = (RoleService) Proxy.newProxyInstance(RoleService.class.getClassLoader(), new Class[]{RoleService.class}, handler);
		service.createRole("2333","1222");
	}

}
