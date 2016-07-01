package com.yin.myproject.demo.pattern.decorator.ex2;

/**
 * 抽象构件角色:被装饰者
 *
 */
public interface CustomerInputStream {
	public void read();

	public void close();
}
