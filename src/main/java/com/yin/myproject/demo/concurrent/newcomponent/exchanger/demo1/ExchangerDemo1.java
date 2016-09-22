package com.yin.myproject.demo.concurrent.newcomponent.exchanger.demo1;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo1 {
	public static void main(String[] args) throws InterruptedException {
		Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.execute(new Consumer(exchanger));
		service.execute(new Producer(exchanger));
		TimeUnit.SECONDS.sleep(1);
		service.shutdownNow();
	}
}
