package com.yin.myproject.demo.io.filestream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 文件输出示例 <br>
 * 基本步骤：<br>
 * 1.打开输出文件<br>
 * 2.声明输出流<br>
 * 3.将字节数组的内容写人输出流<br>
 * 4.关闭输出流<br>
 */
public class FileOutputStreamDemo {
	public static void main(String[] args) throws Exception {
		File file = new File(FileOutputStreamDemo.class.getResource("/").getPath()+"demo.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		String content = "Hello World\n";
		content = content + "123";
		out.write(content.getBytes());
		out.close();
	}
}
