package com.yin.myproject.demo.util;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalTest {

	public static void main(String[] args) {
		System.out.println(0.06 + 0.01);
		System.out.println(10 + 10.26);
		System.out.println(1 + 0.26);
		System.out.println(1.0 - 0.42);
		System.out.println(4.015 * 100);
		System.out.println(303.1 / 1000);
		System.out.println(3.015 * 100);
	}

	@Test
	public void addOfDoubleType() {
		double d1 = 0.10334;
		double d2 = 1234.0;
		System.out.println("new BigDecimal(" + d1 + ")=" + new BigDecimal(d1)); // 此种方式绝对不允许!!!!!
		System.out.println("new BigDecimal(" + d2 + ")=" + new BigDecimal(d2)); // 此种方式绝对不允许!!!!!
		System.out.println("");
		System.out.println("new BigDecimal(String.valueOf(" + d1 + "))=" + new BigDecimal(Double.toString(d1)));
		System.out.println("new BigDecimal(String.valueOf(" + d2 + "))=" + new BigDecimal(Double.toString(d2)));
		System.out.println("");
	}
}
