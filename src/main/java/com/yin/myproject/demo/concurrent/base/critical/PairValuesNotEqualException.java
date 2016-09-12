package com.yin.myproject.demo.concurrent.base.critical;

public class PairValuesNotEqualException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PairValuesNotEqualException() {
		super("Pair values not equal: " + Pair.class);
	}
}
