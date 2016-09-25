package com.yin.myproject.demo.concurrent.base.threadDemo;

public class Demo02 {
	public static void main(String[] args) {
		MyRunnable1 r = new MyRunnable1();
		Thread t = new Thread(r, "我是一个线程");
		/**
		 * getState()方法
		 * 返回类型: Thread.state 一个枚举 表示线程的状态
		 * 具体可参考[线程的生命周期]()
		 */
		System.out.println("线程刚刚声明:状态getState()-->"+t.getState());
		/**
		 * void start()方法 
		 * 启动线程
		 */
		t.start();
		/**
		 * isAlive()方法
		 * 返回类型：boolean
		 * 当线程已经启动并且线程死亡前，线程都处于存活状态
		 */
		System.out.println("线程是否存活:isAlive()-->"+t.isAlive());
		/**
		 * getId()方法
		 * 返回类型 long
		 * 返回当前线程的Id,Id时一个long类型的数字,当线程被创建时自动生成。
		 * 并且该Id唯一不可变，直到线程死亡后才可被复用
		 */
		System.out.println("线程的ID：getId()-->"+t.getId());
		/**
		 * getName()方法
		 * 返回当前线程的名称。java虚拟机默认生成的线程名称格式为："Thread-x",x是一个可变整数
		 */
		System.out.println("线程的名称：getName()-->"+t.getName());
		
	}
}

class MyRunnable1 implements Runnable {

	public void run() {
		System.out.println("线程已启动");
	}
}