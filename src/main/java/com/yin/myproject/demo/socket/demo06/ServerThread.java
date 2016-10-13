package com.yin.myproject.demo.socket.demo06;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket;

	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
		new Thread(this).start();
	}

	public void run() {
		try {
			// 读取客户端数据
			DataInputStream input = new DataInputStream(socket.getInputStream());
			String content = input.readUTF();
			System.out.println("客户端发送的内容为:" + content);
		} catch (IOException e) {
			System.out.println("服务器 run 异常: " + e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
					socket = null;
					System.out.println("服务端 finally 异常:" + e.getMessage());
				}
			}
		}
	}

}
