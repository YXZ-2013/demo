package com.yin.myproject.demo.io.filestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 使用FileInputStream和FileOutputStream完成文件拷贝功能
 *
 */
public class FileCopy {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File src = new File("E:\\workspaceTest\\demo\\pom.xml");
		File dest = new File("C:\\Users\\dasd\\Desktop\\pomcopy.xml");
		new FileCopy().copy(src,dest);
	}

	public void copy(File src, File dest) throws FileNotFoundException, IOException {
		if (!src.exists()) {
			throw new FileNotFoundException("拷贝的文件不存在");
		}
		if (!dest.exists()) {
			dest.createNewFile();
		}
		// 读取文件
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);
		byte[] bytes = new byte[1024];
		int len = -1;
		while ((len = in.read(bytes)) != -1) {
			out.write(bytes, 0, len);
		}
		in.close();
		out.flush();
		out.close();
	}
}
