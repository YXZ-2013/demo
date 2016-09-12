package com.yin.myproject.demo.concurrent.base.critical;

public class PairManager1 extends PairManager{

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(p);
	}

}
