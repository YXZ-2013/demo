package com.yin.myproject.demo.tomcat.ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpServer {
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	private void await() {
		System.out.println(HttpServer.WEB_ROOT);
		ServerSocket serverSocket = null;
		int port = 12345;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

			while (!shutdown) {
				Socket socket = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;

				socket = serverSocket.accept();
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				System.out.println("创建Request对象");
				Request request = new Request(inputStream);
				request.parse();
				System.out.println("Request对象创建完毕");
				
				System.out.println("创建Response对象");
				Response response = new Response(outputStream);
				response.setRequest(request);
				response.sendStaticResource();

				socket.close();

				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
