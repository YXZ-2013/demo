package com.yin.myproject.demo.socket.demo04;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ServerIM {
	public static HashMap<String, Socket> clientMap = new HashMap<String, Socket>();

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8888);
		Socket client = null;
		while (true) {
			System.out.println("等待客户端连接");
			client = server.accept();
			clientMap.put(client.getInetAddress().getHostAddress(), client);
			new Thread(new ServerThread(client)).start();
		}
	}
}

class ServerThread implements Runnable {
	private Socket client = null;
	private Socket client2 = null;
	private String address2 = null;

	public ServerThread(Socket client) {
		this.client = client;
	}

	public void run() {
		try {
			Scanner in = new Scanner(client.getInputStream());
			PrintStream out = new PrintStream(client.getOutputStream());
			System.out.println("客户端" + client.getInetAddress().getHostAddress() + "已经连接");
			out.println("欢迎你是第" + ServerIM.clientMap.size() + "用户");
			out.flush();
			address2 = in.nextLine();
			client2 = ServerIM.clientMap.get(address2);
			if (client2 == null) {
				out.println("你的小伙伴还没有上线请耐心等待");
				while (client2 == null) {
					address2 = in.nextLine();
					client2 = ServerIM.clientMap.get(address2);
				}
			}
			out.println("您已成功和你的小伙伴连上线了你们可以开始通话了");
			PrintStream out2 = new PrintStream(client2.getOutputStream());
			while (true) {
				String call = in.nextLine();
				System.out.println(client.getInetAddress().getHostAddress() + ":" + call);
				if ("exit".equals(call))
					break;
				out2.println(client.getInetAddress().getHostAddress() + ":" + call);
			}
			out2.println("再见");
			System.out.println("客户端" + client.getInetAddress().getHostAddress() + "已经断开");
			client.close();
			client2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}