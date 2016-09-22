package com.yin.myproject.demo.concurrent.newcomponent.exchanger.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
	List<Integer> list = new ArrayList<Integer>();
	Exchanger<List<Integer>> exchanger;

	public Consumer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Consumer before exchager:" + this);
				list = exchanger.exchange(list);
				System.out.println("Consumer after exchager:" + this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Integer value : list) {
			int i = 0;
			sb.append(" " + (++i) + value + " ");
		}
		sb.append("]");
		return sb.toString();
	}

}
