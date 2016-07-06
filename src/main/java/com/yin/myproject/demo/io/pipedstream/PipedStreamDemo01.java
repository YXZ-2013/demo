package com.yin.myproject.demo.io.pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo01 {

	public static void main(String[] args) throws IOException {
		Sender t1 = new Sender();
		Receiver t2 = new Receiver();
		PipedOutputStream out = t1.getOutputStream();
		PipedInputStream in = t2.getInputStream();
		try {
			in.connect(out);
			t1.start();
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// in.close();
			// out.close();
		}
	}

}

class Receiver extends Thread {
	// 管道输入流对象 它和管道输出流对象绑定 从而可以接收管道输出流的数据 再让用户读取
	private PipedInputStream in = new PipedInputStream();

	public PipedInputStream getInputStream() {
		return in;
	}

	@Override
	public void run() {
		try {
			// readMessageOnce();
			readMessageContinued();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从管道流中读取一次数据
	 * 
	 * @throws IOException
	 */
	private void readMessageOnce() throws IOException {
		// 虽然buf的大小是2048个字节,但最多只会从管道流中读取1024个字节 因为 管道输入流的缓冲区大小默认只有1024个字节
		byte[] buf = new byte[2048];
		try {
			int len = in.read(buf);
			System.out.println(new String(buf, 0, len));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}

	public void readMessageContinued() throws IOException {
		int total = 0;
		while (true) {
			byte[] buf = new byte[1024];
			try {
				int len = in.read(buf);
				total += len;
				System.out.println(new String(buf, 0, len));
				if (total > 1024)
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Sender extends Thread {
	// 管道输出流对象 它和管道输入流对象绑定 从而可以将数据发送给管道输入流 然后可以从管道输入流读取数据
	private PipedOutputStream out = new PipedOutputStream();

	public PipedOutputStream getOutputStream() {
		return out;
	}

	// 向管道输入流中写入一则较简短的消息
	private void writeShortMessage() throws IOException {
		String strInfo = "This is a short message";
		try {
			out.write(strInfo.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private void writeLongMessage() throws IOException {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 102; i++) {
			builder.append("0123456789");
		}
		builder.append("abcdefghijklmnopqrstuvwxyz");
		// str的总长度是1020+26=1046个字节
		String str = builder.toString();
		try {
			out.write(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// out.close();
		}
	}

	@Override
	public void run() {
		try {
			// writeShortMessage();
			writeLongMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
