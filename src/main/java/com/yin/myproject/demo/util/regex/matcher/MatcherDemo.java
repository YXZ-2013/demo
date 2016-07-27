package com.yin.myproject.demo.util.regex.matcher;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {

	public static void main(String[] args) {
		// new MatcherDemo().demo1();
		// new MatcherDemo().demo2();
		// new MatcherDemo().demo3();
		new MatcherDemo().demo4();
		// new MatcherDemo().demo5();
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
		int count = m.groupCount();
		int start = m.start();// 2
		String group = m.group();// 22
		int end = m.end();// 4
		System.out.println(count);
		System.out.println(start);
		System.out.println(group);
		System.out.println(end);
	}

	public void demo5() {
		final List<String> variableNames = new LinkedList<String>();
		final String DEFAULT_VARIABLE_PATTERN = "(.*)";
		StringBuilder patternBuilder = new StringBuilder();
		Pattern p = Pattern.compile("\\?|\\*|\\{((\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
		Matcher m = p.matcher("{123333}");
		int end = 0;
		while (m.find()) {
			String match = m.group();
			int groupCount = m.groupCount();
			System.out.println(groupCount);
			System.out.println(m.group());
			System.out.println(m.group(1));
			if (match.startsWith("{") && match.endsWith("}")) {
				int colonIdx = match.indexOf(':');
				if (colonIdx == -1) {
					patternBuilder.append(DEFAULT_VARIABLE_PATTERN);
					variableNames.add(m.group(1));
				}
			}
		}
	}
}
