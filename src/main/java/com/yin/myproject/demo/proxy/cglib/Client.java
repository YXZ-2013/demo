package com.yin.myproject.demo.proxy.cglib;

public class Client {
	public static void main(String[] args) {
//		BookServiceBean service = BookServiceFactory.getInstance();
//		doMethod(service);
//		
		hasAuth();
		hasNoAuth();
	}

	private static void doMethod(BookServiceBean service) {
		service.create();
		service.query();
		service.update();
		service.delete();
	}
	
	public static void hasAuth(){
		BookServiceBean bean = BookServiceFactory.getAuthInstance(new AuthProxy("张三"));
		doMethod(bean);
	}
	
	public static void hasNoAuth(){
		BookServiceBean bean = BookServiceFactory.getAuthInstance(new AuthProxy("李四"));
		doMethod(bean);
	}
}
