package com.yin.myproject.demo.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class AuthProxyFilter implements CallbackFilter {

	public AuthProxyFilter() {

	}

	public int accept(Method method) {
		if (!"query".equalsIgnoreCase(method.getName()))
			return 0;
		return 1;
	}
}
