package com.yin.myproject.demo.io.randomAccessFile;

import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) {
		new RandomAccessFileDemo().demo1();
	}

	private void demo1() {
		try {
			RandomAccessFile rf = new RandomAccessFile("rtest.dat", "rw");
			for (int i = 0; i < 10; i++) {
				rf.writeDouble(i * 1.415);
			}
			rf.close();
			rf = new RandomAccessFile("rtest.dat", "rw");
			rf.seek(5 * 8);
			rf.writeDouble(47.001);
			rf.close();
			rf = new RandomAccessFile("rtest.dat", "rw");
			for (int i = 0; i < 10; i++) {
				System.out.println("Value " + i + ": " + rf.readDouble());
			}
			rf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
