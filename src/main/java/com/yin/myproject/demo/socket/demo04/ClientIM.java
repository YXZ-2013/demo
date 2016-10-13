package com.yin.myproject.demo.socket.demo04;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientIM {
	public static void main(String[] args) throws Exception {
		// 输入服务端主机ip地址
		Socket client = new Socket("localhost", 8888);
		// 发送数据给服务端流
		PrintStream toServer = new PrintStream(client.getOutputStream());

		PrintStream sout = new PrintStream(System.out);
		// 获取服务端数据流
		Scanner getServer = new Scanner(client.getInputStream());
		// 控制台输入流
		Scanner sin = new Scanner(System.in);

		System.out.println("请输入你要通信的小伙伴的ip地址：");
		/* 下面两句话目的是实现输入和刷新实时的作用，使得控制台输入不会阻塞 */
		new Thread(new ClientScanner(sin, toServer)).start();
		new Thread(new ClientScanner(getServer, sout)).start();
	}
}

class ClientScanner implements Runnable {
	Scanner in;
	PrintStream toServer;

	public ClientScanner(Scanner in, PrintStream toServer) {
		this.in = in;
		this.toServer = toServer;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (in.hasNext()) {
			toServer.println(in.nextLine());
		}
	}
}