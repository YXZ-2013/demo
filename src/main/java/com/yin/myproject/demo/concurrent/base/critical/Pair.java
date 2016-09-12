package com.yin.myproject.demo.concurrent.base.critical;

public class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public String toString() {
		return "x: " + x + "y: " + y;
	}

	public void checkState() {
		if (x != y) {
			throw new PairValuesNotEqualException();
		}
	}
}
