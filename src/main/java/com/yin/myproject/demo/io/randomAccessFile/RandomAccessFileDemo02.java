package com.yin.myproject.demo.io.randomAccessFile;

import java.io.RandomAccessFile;

/**
 * 从任意位置读取数据
 * 
 * @author dasd
 *
 */
public class RandomAccessFileDemo02 {

	public static void main(String[] args) {
		// 声明RandomAccessFile
		try {
			RandomAccessFile file = new RandomAccessFile(
					RandomAccessFileDemo02.class.getResource("/").getPath() + "test.txt", "r");
			// 获取指针位置
			long pointer = 20;
			System.out.println("RandomAccessFile文件指针的初始位置：" + pointer);
			// 移动指针位置
			file.seek(pointer);
			byte[] buff = new byte[1024];
			int hasRead = 0;
			while ((hasRead = file.read(buff)) > 0) {
				System.out.println(new String(buff, 0, hasRead));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
