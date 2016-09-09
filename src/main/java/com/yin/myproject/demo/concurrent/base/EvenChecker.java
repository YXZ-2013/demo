package com.yin.myproject.demo.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator g, int ident) {
		this.generator = g;
		this.id = ident;
	}

	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even");
				generator.cancel();
			}
			if (val == 100000) {
				System.out.println("已到100000");
				generator.cancel();
				System.exit(0);
			}

		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C exit");
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			service.execute(new EvenChecker(gp, i));
		}
		service.shutdown();
	}

	public static void test(IntGenerator gp) {
		test(gp, 10);
	}
}
