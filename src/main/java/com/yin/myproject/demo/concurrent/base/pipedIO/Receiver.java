package com.yin.myproject.demo.concurrent.base.pipedIO;

import java.io.IOException;
import java.io.PipedReader;

public class Receiver implements Runnable {
	private PipedReader in;

	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}

	public void run() {
		try {
			while (true) {
				System.out.println("Read: " + (char) in.read() + ". ");
			}
		} catch (IOException e) {
			System.out.println(e + "Reciver read exception");
		}
	}

}
