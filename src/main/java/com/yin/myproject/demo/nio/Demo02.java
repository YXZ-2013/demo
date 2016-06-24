package com.yin.myproject.demo.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo02 {
	public static void main(String[] args) {
		try {
			RandomAccessFile aFile = new RandomAccessFile("rtest.dat", "rw");
			FileChannel inChannel = aFile.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocate(48);
			int byteRead = inChannel.read(buf);
			
			while(byteRead != -1){
				buf.flip();
				
				while(buf.hasRemaining()){
					System.out.println((long)buf.get());
				}
				
				buf.clear();
				byteRead = inChannel.read(buf);
			}
			
			aFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
