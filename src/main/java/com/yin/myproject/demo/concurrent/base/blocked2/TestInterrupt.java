package com.yin.myproject.demo.concurrent.base.blocked2;

import java.util.concurrent.TimeUnit;

public class TestInterrupt {
	public static void main(String[] args) {
		Thread thread1 = new Thread() {
			public void run() {
				try {
					// TimeUnit.SECONDS.sleep(5);
					long time = System.currentTimeMillis();
					while (System.currentTimeMillis() - time < 2000) {

					}
					System.out.println("A1");
				} catch (Exception e) {
					System.out.println("B1");
				}
			}
		};
		thread1.start();
		thread1.interrupt();

		// 在线程sleep状态下进行中断
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("A2");
				} catch (Exception e) {
					System.out.println("B2");
				}
			}
		};
		thread2.start();
		thread2.interrupt();

		// 在线程wait状态下进行中断,其中wait()在同步块中
		Thread thread3 = new Thread() {
			@Override
			public void run() {
				try {
					this.wait(2000);
					System.out.println("A3");
				} catch (Exception e) {
					System.out.println("B3");
				}
			}
		};
		thread3.start();
		thread3.interrupt();
		

		// 在线程wait状态下进行中断，其中wait()不在同步块中
		Thread thread4 = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						this.wait(2000);
						System.out.println("A4");
					}
				} catch (Exception e) {
					System.out.println("B4");
				}
			}
		};
		thread4.start();
		thread4.interrupt();

		try {
			thread4.start();
			System.out.println("A5");
		} catch (Exception e) {
			System.out.println("B5");
			System.out.println(e.toString());
		}
	}
}
