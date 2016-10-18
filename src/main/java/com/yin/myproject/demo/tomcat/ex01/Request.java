package com.yin.myproject.demo.tomcat.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Eason on 2016/10/11 16:06 16:07.
 */
public class Request {
	private InputStream inputStream;
	private String uri;

	public Request(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void parse() {
		StringBuffer request = new StringBuffer(2048);
	    int i;
	    byte[] buffer = new byte[2048];
	    try {
	      i = inputStream.read(buffer);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	      i = -1;
	    }
	    for (int j=0; j<i; j++) {
	      request.append((char) buffer[j]);
	    }
	    System.out.print(request.toString());
		System.out.println("读取请求内容完毕");
		uri = parseUri(request.toString());
		System.out.println("uri:" + uri);
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
