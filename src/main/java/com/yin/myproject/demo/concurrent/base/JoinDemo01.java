package com.yin.myproject.demo.concurrent.base;

public class JoinDemo01 implements Runnable {
	private static int n = 0;

	public void run() {
		for (int i = 0; i < 5; i++) {
			n += 1;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new JoinDemo01());
		t.start();
		/*
		 * 注释一 TimeUnit.SECONDS.sleep(10);
		 */
		/*
		 * 注释二 t.join();
		 */
		t.join();
		System.out.println(n);
	}

}
