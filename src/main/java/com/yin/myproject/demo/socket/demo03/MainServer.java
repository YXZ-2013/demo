package com.yin.myproject.demo.socket.demo03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(30000);
		while(true){
			System.out.println("等待客户端的访问");
			Socket socket = serverSocket.accept();
			socketList.add(socket);
			new Thread(new ServerThread(socket)).start();
		}
	}
}
