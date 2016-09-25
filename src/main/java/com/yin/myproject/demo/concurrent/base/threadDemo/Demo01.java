package com.yin.myproject.demo.concurrent.base.threadDemo;

public class Demo01 {
	public static void main(String[] args) {
		/* 这里使用了空构造函数public Thread() */
		Thread t1 = new Thread();
		Thread t2 = new MyThread("线程名字");
		/* 使用构造函数public Thread(Runnable target) target是线程执行的任务 */
		Thread t3 = new Thread(new MyRunnable());
		/* public Thread(Runnable target,String name) name是线程名称 target是线程执行的任务 */
		Thread t4 = new Thread(new MyRunnable(), "123");
	}
}

class MyThread extends Thread {

	/**
	 * 这里使用了构造函数public Thread(String name),name参数表示线程名称
	 * 
	 * @param name
	 */
	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println("线程已启动");
	}
}

class MyRunnable implements Runnable {

	public void run() {
		System.out.println("线程已启动");
	}
}