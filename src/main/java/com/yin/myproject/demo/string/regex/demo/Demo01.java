package com.yin.myproject.demo.string.regex.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用正则表达式匹配小数点后不超过2位的数字
 * 
 * @author dasd
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		String regex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("123");
		System.out.println(matcher.matches());
		matcher = pattern.matcher("123.000");
		System.out.println(matcher.matches());
		matcher = pattern.matcher("0");
		System.out.println(matcher.matches());
		matcher = pattern.matcher("0.0");
		System.out.println(matcher.matches());
		matcher = pattern.matcher("0.00");
		System.out.println(matcher.matches());
		matcher = pattern.matcher("0.001");
		System.out.println(matcher.matches());
	}
}
