package com.yin.myproject.demo.socket.demo02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
		Socket socket = serverSocket.accept();
		System.out.println("最新请求,请求URL为" + socket.getInetAddress());
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		byte[] bytes = new byte[1024];
		int hasRead = 0;
		while ((hasRead = is.read(bytes)) > 0) {
			System.out.println(new String(bytes, 0, hasRead));
		}
		String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
				+ "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
		os.write(errorMessage.getBytes());
		socket.close();
//		serverSocket.close();
	}
}
