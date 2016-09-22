package com.yin.myproject.demo.concurrent.newcomponent.exchanger.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
	List<Integer> list = new ArrayList<Integer>();
	Exchanger<List<Integer>> exchanger = null;

	public Producer(Exchanger<List<Integer>> exchanger) {
		this.exchanger = exchanger;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Producer before exchanger" + this);
				Random rand = new Random();
				for (int i = 0; i < 10; i++) {
					list.clear();
					list.add(rand.nextInt(10000));
					list.add(rand.nextInt(10000));
					list.add(rand.nextInt(10000));
					list.add(rand.nextInt(10000));
					list.add(rand.nextInt(10000));
					list.add(rand.nextInt(10000));
				}
				list = exchanger.exchange(list);
				System.out.println("Producer after exchanger" + this);
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
