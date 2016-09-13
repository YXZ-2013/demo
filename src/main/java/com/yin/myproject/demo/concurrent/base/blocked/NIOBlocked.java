package com.yin.myproject.demo.concurrent.base.blocked;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOBlocked implements Runnable {
	private final SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	public void run() {
		try {
			System.out.println("Waiting for read() in " + this);
			sc.read(ByteBuffer.allocate(1));
		} catch (IOException e) {
			throw new RuntimeException();
		}
		System.out.println("Exiting NIOBlocked.run() " + this);
	}

}
