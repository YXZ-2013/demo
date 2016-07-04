package com.yin.myproject.demo.io.filestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件输入流demo
 *
 */
public class FileInputStreamDemo {
	public static void main(String[] args) throws Exception {
		String filePath = FileInputStreamDemo.class.getResource("/").getPath();
		System.out.println(filePath);
		InputStream inputStream = new FileInputStream(filePath + "test.txt");
		OutputStream outputStream = new FileOutputStream(filePath + "test1.txt");
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		outputStream.write(bytes);
		inputStream.close();
		outputStream.close();
	}
}
