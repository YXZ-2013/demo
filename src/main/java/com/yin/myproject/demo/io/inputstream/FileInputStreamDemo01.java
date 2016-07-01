package com.yin.myproject.demo.io.inputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileInputStreamDemo01 {
	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(FileInputStreamDemo.class.getResource("/123.txt").getPath());
		OutputStream out = new FileOutputStream(FileInputStreamDemo.class.getResource("/test1.txt").getPath());
		byte[] bytes = new byte[1024];
		int hasRead = 0;
		while((hasRead = in.read(bytes))>0){
			System.out.println(new String(bytes,0,hasRead));
			out.write(bytes,0,hasRead);
			out.flush();
		}
		out.close();
		in.close();
	}
}
