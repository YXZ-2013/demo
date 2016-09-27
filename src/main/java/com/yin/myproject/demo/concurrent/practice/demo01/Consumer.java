package com.yin.myproject.demo.concurrent.practice.demo01;

public class Consumer implements Runnable {
	// 简单的模拟，这里一个生产容器，设置成final类型的话不允许再次赋值
	private final Container<Bread> container;
	// 生产者线程监听器
	private final Object producerMonitor;
	// 消费者线程监听器
	private final Object consumerMonitor;

	public Consumer(Object producerMonitor, Object consumerMonitor, Container<Bread> container) {
		this.producerMonitor = producerMonitor;
		this.consumerMonitor = consumerMonitor;
		this.container = container;
	}

	public void run() {
		while (true) {
			consume();
		}
	}

	private void consume() {
		if (container.isEmpty()) {
			System.out.println("容器已空");
			// 唤醒生产者
			synchronized (producerMonitor) {
				if (container.isEmpty()) {
					producerMonitor.notify();
				}
			}
			// 消费者挂起
			synchronized (consumerMonitor) {
				try {
					if (container.isEmpty()) {
						System.out.println("消费者挂起。。。");
						consumerMonitor.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("容器内剩余面包:"+container.getSize());
			Bread bread = container.get();
			System.out.println("brad:" + bread);
		}
	}
}
