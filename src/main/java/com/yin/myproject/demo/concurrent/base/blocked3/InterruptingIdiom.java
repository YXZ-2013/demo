package com.yin.myproject.demo.concurrent.base.blocked3;

import java.util.concurrent.TimeUnit;

public class InterruptingIdiom {
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		if(args.length!=1){
			System.out.println("usage: java InterruptingIdiom delay-in-m5");
			System.exit(1);
		}
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		t.interrupt();
	}
}
