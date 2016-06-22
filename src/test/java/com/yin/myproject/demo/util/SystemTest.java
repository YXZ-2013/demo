package com.yin.myproject.demo.util;

import java.util.Map;

import org.junit.Test;

public class SystemTest {

	
	@Test
	public void fieldTest() {
		System.out.println("-->out");
		System.err.println("-->err");
		
		System.out.println("-------------------------------------------");
	}

	@Test
	public void methodTest() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {

		}
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println(interval);
		System.out.println("-------------------------------------------");

		Map<String, String> env = System.getenv();
		for (String key : env.keySet()) {
			System.out.println(key + "--->" + env.get(key));
		}
		System.out.println("-------------------------------------------");
		int[] a = { 1, 2, 3, 4 };
		int[] b = new int[5];
		System.arraycopy(a, 2, b, 0, 2);
		for (int i = 0; i < b.length; i++) {
			System.out.println("b[" + i + "]==" + b[i]);
		}
	}
}
