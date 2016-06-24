package com.yin.myproject.demo.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileUtil {

	/**
	 * 文件末尾追加内容
	 * 
	 * @param path
	 */
	public static void appendTo(String path) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(path, "rw");
			aFile.seek(aFile.length());
			aFile.write("this is appened content \r\n".getBytes());
			aFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向指定位置插入数据
	 * 
	 * @param fileName
	 * @param points
	 * @param content
	 */
	@SuppressWarnings("resource")
	public static void insert(String fileName, long points, String content) {
		File temp = null;
		try {
			temp = File.createTempFile("temp", null);

			RandomAccessFile aFile = new RandomAccessFile(fileName, "rw");
			// 创建一个临时文件来保存插入点后的数据
			FileOutputStream tmpOut = new FileOutputStream(temp);
			FileInputStream tmpIn = new FileInputStream(temp);
			aFile.seek(points);
			/* 将插入点后的内容读入临时文件中 */
			byte[] buff = new byte[1024];

			// 用于保存临时读取的字节数
			int hasRead = 0;
			while ((hasRead = tmpIn.read(buff)) > 0) {
				// 将读取的数据写入临时文件
				tmpOut.write(buff, 0, hasRead);
			}

			// 插入需要制定添加的数据
			aFile.seek(points);
			aFile.write(content.getBytes());

			// 最后追加临时文件中的内容
			while ((hasRead = tmpIn.read(buff)) > 0) {
				aFile.write(buff, 0, hasRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != temp) {
				temp.delete();
			}
		}
	}

	public static void main(String[] args) {
		// RandomAccessFileUtil.appendTo(RandomAccessFileUtil.class.getResource("/").getPath()+"123");
		// RandomAccessFileUtil.appendTo(RandomAccessFileUtil.class.getResource("/").getPath()+"123.txt");
		String content = "RandomAccessFile支持搜寻方法，并且只适用于文件。RandomAccessFile支持搜寻方法，并且只适用于文件。RandomAccessFile支持搜寻方法，并且只适用于文件。RandomAccessFile支持搜寻方法，并且只适用于文件。RandomAccessFile支持搜寻方法，并且只适用于文件。";
		RandomAccessFileUtil.insert(RandomAccessFileUtil.class.getResource("/").getPath() + "123.txt", 33, content);

	}
}
