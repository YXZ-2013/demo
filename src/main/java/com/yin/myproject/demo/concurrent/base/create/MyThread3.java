package com.yin.myproject.demo.concurrent.base.create;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyThread3 implements Callable<Integer> {

	public Integer call() throws Exception {
		System.out.println("通过实现Callable接口创建一个线程:返回一个1-100的随机整数");
		return new Random().nextInt(100);
	}

}
