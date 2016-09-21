package com.yin.myproject.demo.concurrent.newcomponent.pbq.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();

	public PrioritizedTask(int priority) {
		super();
		this.priority = priority;
	}

	public int compareTo(PrioritizedTask p) {
		return priority < p.priority ? 1 : (priority > p.priority ? -1 : 0);
	}

	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this);
	}

	@Override
	public String toString() {
		return String.format("[%1$-3d]", priority) + " Task " + id;
	}

	public String summary() {
		return "(" + id + ":" + priority + ")";
	}

	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;

		public EndSentinel(ExecutorService exec) {
			super(-1);
			this.exec = exec;
		}

		@Override
		public void run() {
			int count = 0;
			for (PrioritizedTask pt : sequence) {
				System.out.print(pt.summary());
				if (++count % 5 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.print(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}

	}

}
