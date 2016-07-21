package com.yin.myproject.demo.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AuthProxy implements MethodInterceptor{

	private String name;
	
	public AuthProxy(String name){
		this.name = name;
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if(!"张三".equals(name)){
			System.out.println("没有权限");
			return null;
		}
		return proxy.invokeSuper(obj, args);
	}
}
