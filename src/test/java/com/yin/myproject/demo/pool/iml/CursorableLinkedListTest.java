package com.yin.myproject.demo.pool.iml;

import org.junit.Before;
import org.junit.Test;

import com.yin.myproject.demo.common.pool.impl.CursorableLinkedList;

public class CursorableLinkedListTest {
	private CursorableLinkedList<String> list = null;
	
	@Before
	public void newInstance(){
		list = new CursorableLinkedList<String>();
	}
	
	@Test
	public void add(){
		System.out.println(1);
		list.add("1");
		list.add("2");
		System.out.println(1);
	}
}
