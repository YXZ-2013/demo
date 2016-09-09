package com.yin.myproject.demo.concurrent.base;

/**
 * 抽象类包含EvenChecker必不可少的方法: 一个next()方法和一个可以执行撤销的方法cancel()
 * 
 * @author XunzhiYin
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}
