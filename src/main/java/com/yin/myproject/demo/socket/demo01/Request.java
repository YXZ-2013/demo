package com.yin.myproject.demo.socket.demo01;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Eason on 2016/10/11 16:06 16:07.
 */
public class Request {
	private InputStream input;
	private String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	public void parse() {
		StringBuffer request = new StringBuffer(1024);
		byte[] bytes = new byte[1024];
		int hasRead = 0;
		try {
			while ((hasRead = input.read(bytes)) > 0) {
				System.out.println(new String(bytes, 0, hasRead));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(request.toString());
		uri = parseUri(request.toString());
	}

	private String parseUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}

	public String getUri() {
		return uri;
	}
}
