package com.yin.myproject.demo.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class BookServiceFactory {
	private static BookServiceBean service = new BookServiceBean();

	public BookServiceFactory() {
	}

	public static BookServiceBean getInstance() {
		return service;
	}

	public static BookServiceBean getAuthInstance(AuthProxy authProxy) {
		Enhancer en = new Enhancer();
		en.setSuperclass(BookServiceBean.class);
		en.setCallback(authProxy);
		return (BookServiceBean) en.create();
	}

	public static BookServiceBean getAuthInstanceByFilter(AuthProxy authProxy) {
		Enhancer en = new Enhancer();
		en.setSuperclass(BookServiceBean.class);
		en.setCallbacks(new Callback[] { authProxy, NoOp.INSTANCE });
		en.setCallbackFilter(new AuthProxyFilter());
		return (BookServiceBean) en.create();
	}
}
