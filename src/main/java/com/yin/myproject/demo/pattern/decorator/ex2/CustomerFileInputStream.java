package com.yin.myproject.demo.pattern.decorator.ex2;

/**
 * 具体构件角色:被装饰类
 *
 */
public class CustomerFileInputStream implements CustomerInputStream {

	public void read() {
		System.out.println("文件输入流读取。。。");
	}

	public void close() {
		System.out.println("文件输入流关闭。。。");
	}

}
