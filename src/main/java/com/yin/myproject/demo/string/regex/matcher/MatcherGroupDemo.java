package com.yin.myproject.demo.string.regex.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherGroupDemo {

	public static void main(String[] args) {
		System.out.println("--------------Result of demo1()--------------");
		new MatcherGroupDemo().demo1();
		System.out.println("--------------Result of demo2()--------------");
		// new MatcherGroupDemo().demo2();
		System.out.println("--------------Result of demo3()--------------");
		// new MatcherGroupDemo().demo3();
		System.out.println("--------------Result of demo4()--------------");
		new MatcherGroupDemo().demo4();
		System.out.println("--------------Result of demo5()--------------");
		new MatcherGroupDemo().demo5();
	}

	public void demo1() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "(John)";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}

	public void demo2() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "John";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}

	public void demo3() {
		String text = "John writes about this, and John writes about that," + " and John writes about everything. ";
		String patternString1 = "(?:John)";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		System.out.println("groupCount is -->" + matcher.groupCount());
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
		}
	}

	public void demo4() {
		String text = "John writes about this, and John Doe writes about that,"
				+ " and John Wayne writes about everything.";
		String patternString1 = "(John) (.+?) ";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		matcher.find();// 匹配字符串,匹配到的字符串可以在任何位置
		// int start = matcher.start();//返回当前匹配到的字符串在原目标字符串中的位置
		// int end = matcher.end();//返回当前匹配的字符串的最后一个字符在原目标字符串中的索引位置
		System.out.println("found group: group(0) is '" + matcher.group(0));
		System.out
				.println("found group: group(1) is '" + matcher.group(1) + "',group(2) is '" + matcher.group(2) + "'");
	}

	public void demo5() {
		String text = "John writes about this, and John Doe writes about that,"
				+ " and John Wayne writes about everything.";
		String patternString1 = "(John) (.+?) ";
		Pattern pattern = Pattern.compile(patternString1);
		Matcher matcher = pattern.matcher(text);
		matcher.find();// 匹配字符串,匹配到的字符串可以在任何位置
		int start = matcher.start();// 返回当前匹配到的字符串在原目标字符串中的位置
		System.out.println(start);// 0
		int end = matcher.end();// 返回当前匹配的字符串的最后一个字符在原目标字符串中的索引位置
		System.out.println(end);// 12
		start = matcher.start(1);// 第一个分组匹配的内容,也就是John开始的索引位置,0
		System.out.println(start);// 0
		start = matcher.start(2);// 第一个分组匹配的内容,也就是writes开始的索引位置,5
		System.out.println(start);// 5
		end = matcher.end(1);// 第一个分组匹配的内容,也就是John结束的索引位置,4
		System.out.println(end);// 4
		end = matcher.end(2);// 第二个分组匹配的内容,也就是writes开始的索引位置,12
		System.out.println(end);// 12
		start = matcher.start(3);// Exception in thread "main"
									// java.lang.IndexOutOfBoundsException: No
									// group 3
	}
}
