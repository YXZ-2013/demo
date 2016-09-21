package com.yin.myproject.demo.concurrent.newcomponent.delayQueue.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}

	public void run() {
		System.out.println(this + " ");
	}

	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public String summary() {
		return "(" + id + ":" + delta + ")";
	}

	@Override
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}

	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;

		public EndSentinel(int delay, ExecutorService exec) {
			super(delay);
			this.exec = exec;
		}

		@Override
		public void run() {
			for (DelayedTask pt : sequence) {
				System.out.println(pt.summary() + "  ");
			}
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdown();
		}

	}

}
