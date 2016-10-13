package com.yin.myproject.demo.socket.demo06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static final String IP_ADDR = "localhost";// 服务器地址
	public static final int PORT = 12345;// 服务器端口号

	public static void main(String[] args) {
		System.out.println("客户端启动");
		System.out.println("当接收到服务器端字符为\"OK\"的时候，客户端将终止");
		while (true) {
			Socket socket = null;
			DataOutputStream out = null;
			// 创建一个流套接字并将其连接到指定主机上的指定端口号
			try {
				socket = new Socket(IP_ADDR, PORT);
				// 向服务器端发送数据
				out = new DataOutputStream(socket.getOutputStream());
				System.out.print("请输入: \t");
				String content = new BufferedReader(new InputStreamReader(System.in)).readLine();
				System.out.println("向服务端发送的数据为：" + content);
				out.writeUTF(content);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
