package com.yin.myproject.demo.socket.demo06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int PORT = 12345;// 监听的端口号

	public static void main(String[] args) {
		System.out.println("服务器启动......");
		Server server = new Server();
		server.init();
	}

	private void init() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(PORT);
			// 获取客户端的连接
			Socket client = serverSocket.accept();
			new ServerThread(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
