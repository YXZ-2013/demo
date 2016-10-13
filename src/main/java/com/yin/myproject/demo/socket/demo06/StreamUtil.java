package com.yin.myproject.demo.socket.demo06;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	public static String readInputStream(InputStream is) {
		try {
			StringBuffer sb = new StringBuffer();
			byte[] bytes = new byte[1024];
			int hasRead = 0;
			while ((hasRead = is.read(bytes)) > 0) {
				sb.append(new String(bytes, 0, hasRead));
			}
			return sb.toString();
		} catch (IOException e) {
			System.out.println("读取字节流失败");
			e.printStackTrace();
		}
		return "";
	}

}
