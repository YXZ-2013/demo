package com.yin.myproject.demo.concurrent.base.blokedqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.yin.myproject.demo.concurrent.base.LiftOff;

public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for (int i = 0; i < 5; i++) {
			runner.add(new LiftOff(5));
		}
		getKey("Press 'Enter' (" + msg + ")" + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}

	public static void main(String[] args) {
		test("LinkedBLockingQueue", new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());
	}
}
