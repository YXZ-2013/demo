package com.yin.myproject.demo.spring.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionDemo {
	
	public static void main(String[] args) {
		new DependencyInjectionDemo().DIDemo01();
	}
	
	public void DIDemo01(){
		BeanFactory beanFactory  = new ClassPathXmlApplicationContext("/spring-config/di-demo01.xml");
	}
}
