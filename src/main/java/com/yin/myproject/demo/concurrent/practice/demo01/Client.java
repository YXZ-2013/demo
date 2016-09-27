package com.yin.myproject.demo.concurrent.practice.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		Object producerMonitor = new Object();
		Object consumerMonitor=new Object();
		Container<Bread> container = new Container<Bread>(10);
		//生产者启动
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Producer(container,producerMonitor,consumerMonitor));
		service.execute(new Producer(container,producerMonitor,consumerMonitor));
		service.execute(new Producer(container,producerMonitor,consumerMonitor));
		service.execute(new Producer(container,producerMonitor,consumerMonitor));
		//消费者启动
		service.execute(new Consumer(producerMonitor, consumerMonitor, container));
		service.execute(new Consumer(producerMonitor, consumerMonitor, container));
		TimeUnit.SECONDS.sleep(5);
		service.shutdown();
	}
}
