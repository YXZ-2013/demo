package com.yin.myproject.demo.util.regex.matcher;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class PatternDemo {

	public static void main(String[] args) {
		System.out.println("-------executing demo1 -------");
		new PatternDemo().demo1();
		System.out.println("-------executing demo2 -------");
		new PatternDemo().demo2();
	}

	public void demo1() {
		String regex = "\\?|\\*";
		Pattern pattern = Pattern.compile(regex);
		System.out.println("Create the Pattern instance by Pattern.compile(String regex) method" + regex);
		String patternStr = pattern.pattern();
		System.out.println("Result of Pattern.pattern()-->" + patternStr);
		int flags = pattern.flags();
		System.out.println("Result of pattern.flags()-- > " + flags);
		String[] splitStrs = pattern.split("?123?123*456*456");
		System.out.print("Result of pattern.split(CharSequence input) --> ");
		for (String string : splitStrs) {
			System.out.print(string + " ");
		}
		System.out.println();
		String[] splitStrs2 = pattern.split("?123?123*456*456", 2);
		System.out.print("Result of pattern.split(CharSequence input,int limit) --> ");
		for (String string : splitStrs2) {
			System.out.print((StringUtils.isEmpty(string) ? "null " : string) + " ");
		}
		System.out.println();

		@SuppressWarnings("static-access")
		boolean matches = pattern.matches(regex, "?");
		System.out.println(matches);
		
		
		String quote = Pattern.quote("123?123*456*456");
		System.out.println(quote);
	}

	private void demo2() {

	}
}
