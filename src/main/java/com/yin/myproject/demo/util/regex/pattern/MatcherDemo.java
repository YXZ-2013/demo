package com.yin.myproject.demo.util.regex.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {
	public static void main(String[] args) {
		// new MatcherDemo().demo1();
		// new MatcherDemo().demo2();
		// new MatcherDemo().demo3();
		new MatcherDemo().demo4();
	}

	public void demo1() {
		Pattern pattern = Pattern.compile("\\?{2}");
		Matcher matcher = pattern.matcher("??");
		boolean matches = matcher.matches();// true
		System.out.println(matches);
		matcher = pattern.matcher("?");
		matches = matcher.matches();// false
		System.out.println(matches);
	}

	public void demo2() {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("22bb23");
		boolean match = m.lookingAt();// true
		System.out.println(match);
		m = p.matcher("bb2233");
		match = m.lookingAt();
		System.out.println(match);// false
	}

	public void demo3() {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("22bb23");
		m.find();// 返回true
		Matcher m2 = p.matcher("aa2223");
		m2.find();// 返回true
		Matcher m3 = p.matcher("aa2223bb");
		m3.find();// 返回true
		Matcher m4 = p.matcher("aabb");
		m4.find();// 返回false
	}

	public void demo4() {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher("aa22bb23");
		m.find();
		int start = m.start();//2
		String group = m.group();//22
		int end = m.end();//4
		System.out.println(start);
		System.out.println(group);
		System.out.println(end);
	}
}
