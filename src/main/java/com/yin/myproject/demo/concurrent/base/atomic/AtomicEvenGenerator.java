package com.yin.myproject.demo.concurrent.base.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import com.yin.myproject.demo.concurrent.base.EvenChecker;
import com.yin.myproject.demo.concurrent.base.IntGenerator;

public class AtomicEvenGenerator extends IntGenerator{
	private AtomicInteger currentEvenValue = new AtomicInteger(0);
	
	@Override
	public int next() {
		return currentEvenValue.addAndGet(2);
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
	
}
