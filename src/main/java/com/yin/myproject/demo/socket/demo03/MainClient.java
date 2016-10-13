package com.yin.myproject.demo.socket.demo03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("客户端启动...");
		System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");
		while (true) {
			Socket clientSocket = new Socket("127.0.0.1", 30000);
			// 读取服务器端数据
			String content = readFromServer(clientSocket);
			System.out.println("接收到的数据为:" + content);
			// 向服务器端发送
			OutputStream os = clientSocket.getOutputStream();
			DataOutputStream out = new DataOutputStream(os);
			System.out.print("请输入: \t");
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
			out.writeUTF(str);
		}
	}

	private static String readFromServer(Socket clientSocket) {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = clientSocket.getInputStream();
			int hasRead = 0;
			byte[] bytes = new byte[1024];
			while ((hasRead = is.read(bytes)) > 0) {
				sb.append(new String(bytes, 0, hasRead));
			}
			return sb.toString();
		} catch (IOException e) {

		}
		return null;
	}
}
