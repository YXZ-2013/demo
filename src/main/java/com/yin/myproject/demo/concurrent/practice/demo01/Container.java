package com.yin.myproject.demo.concurrent.practice.demo01;

import java.util.ArrayList;
import java.util.List;

public class Container<T> {
	private final int capacity;
	private final List<T> list;

	public Container(int capacity) {
		super();
		this.capacity = capacity;
		list = new ArrayList<T>(capacity);
	}

	public List<T> getList() {
		return list;
	}

	public synchronized boolean add(T product) {
		if (list.size() < capacity) {
			list.add(product);
			return true;
		}
		return false;
	}

	public synchronized boolean isFull() {
		if (list.size() >= capacity) {
			return true;
		}
		return false;
	}

	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}

	public synchronized T get() {
		if (list.size() > 0) {
			return list.remove(0);
		}
		return null;
	}

	public synchronized int getSize() {
		return list.size();
	}

	public int getCapacity() {
		return capacity;
	}

}
