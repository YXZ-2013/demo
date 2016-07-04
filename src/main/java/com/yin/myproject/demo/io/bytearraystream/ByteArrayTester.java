package com.yin.myproject.demo.io.bytearraystream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayTester {

	public static void main(String[] args) throws IOException {
		new ByteArrayTester().demo1();

	}

	public void demo1() throws IOException {
		byte[] buff = new byte[] { 2, 15, 66, -1, -9, 9 };
		ByteArrayInputStream in = new ByteArrayInputStream(buff);
		int data = in.read();
		while (data != -1) {
			System.out.println(data + " ");
			data = in.read();
		}
		in.close();
	}
}
