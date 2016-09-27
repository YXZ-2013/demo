package com.yin.myproject.demo.concurrent.practice.demo01;

public class Producer implements Runnable {
	// 简单的模拟，这里一个生产容器，设置成final类型的话不允许再次赋值
	private final Container<Bread> container;
	// 生产者线程监听器
	private final Object producerMonitor;
	// 消费者线程监听器
	private final Object consumerMonitor;

	public Producer(Container<Bread> container, Object producerMonitor, Object consumerMonitor) {
		this.container = container;
		this.producerMonitor = producerMonitor;
		this.consumerMonitor = consumerMonitor;
	}

	public void run() {
		while (true) {
			produce();
		}
	}

	private void produce() {
		// 这里为了形象，模拟几个制作面包的步骤
		step1();
		Bread bread = step2();
		if (container.isFull()) {
			System.out.println("容器已满....");
			// 唤醒消费者
			synchronized (consumerMonitor) {
				if (container.isFull()) {
					consumerMonitor.notify();
				}
			}
			synchronized (producerMonitor) {
				try {
					if (container.isFull()) {
						System.out.println("生产者挂起...");
						producerMonitor.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			boolean result = container.add(bread);
			System.out.println("Producer:" + result);
		}
	}

	public void step1() {
	}

	public Bread step2() {
		return new Bread();
	}
}
