package com.yin.myproject.demo.concurrent.base.pipedIO;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(sender);
		service.execute(receiver);
		TimeUnit.SECONDS.sleep(5);
		service.shutdownNow();
	}
}
