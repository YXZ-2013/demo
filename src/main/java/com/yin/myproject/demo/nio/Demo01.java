package com.yin.myproject.demo.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo01 {
	
	public static void main(String[] args) {
		try {
			RandomAccessFile aFile = new RandomAccessFile("rtest.dat", "rw");
			FileChannel inChannl = aFile.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocate(48);
			
			int bytesRead = inChannl.read(buf);
			while(bytesRead != -1){
				System.out.println("Read " + bytesRead);
				buf.flip();
				
				while(buf.hasRemaining()){
					System.out.println((long)buf.get());
				}
				
				buf.clear();
				bytesRead = inChannl.read(buf);
			}
			aFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
