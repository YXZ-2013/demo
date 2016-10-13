package com.yin.myproject.demo.socket.demo03;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	Socket socket = null;

	public ServerThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
	}

	public void run() {
		// 从客户端读取内容
		String content = readFromClient();
		System.out.println("客户端发出的内容:" + content);
	}

	private String readFromClient() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = socket.getInputStream();
			int hasRead = 0;
			byte[] bytes = new byte[1024];
			while ((hasRead = is.read(bytes)) > 0) {
				sb.append(new String(bytes, 0, hasRead));
			}
			return sb.toString();
		} catch (IOException e) {
			MainServer.socketList.remove(socket);
		}
		return null;
	}

}
