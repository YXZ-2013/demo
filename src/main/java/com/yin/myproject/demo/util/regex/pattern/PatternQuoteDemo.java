package com.yin.myproject.demo.util.regex.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternQuoteDemo {
	public static void main(String[] args) {
		new PatternQuoteDemo().demo2();
	}

	public void demo1() {
		Pattern pattern = Pattern.compile(".*");
		Matcher matcher = pattern.matcher("123");
		boolean matches = matcher.matches();// true
		System.out.println(matches);
		matcher = pattern.matcher("foo");
		matches = matcher.matches();// true
		System.out.println(matches);
	}

	public void demo2() {
		String regex = Pattern.quote(".*");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("123");
		boolean match = matcher.matches();// false
		System.out.println(match);
		matcher = pattern.matcher("foo");// false
		System.out.println(match);
		matcher = pattern.matcher(".*");
		match = matcher.matches();// true
		System.out.println(match);

	}
}
