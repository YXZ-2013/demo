package com.yin.myproject.demo.concurrent.base.blocked;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from bloked I/O");
			} else {
				Thread.interrupted();
//				throw new RuntimeException();
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}

}
