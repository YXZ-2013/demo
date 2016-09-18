package com.yin.myproject.demo.concurrent.base.toastOmatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ToastOMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue();
		ToastQueue butteredQueue = new ToastQueue();
		ToastQueue finishedQueue = new ToastQueue();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Toaster(dryQueue));
		service.execute(new Butterer(dryQueue, butteredQueue));
		service.execute(new Jammer(butteredQueue, finishedQueue));
		service.execute(new Easter(finishedQueue));
		TimeUnit.SECONDS.sleep(15);
		service.shutdownNow();
	}
}
