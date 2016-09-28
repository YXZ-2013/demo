package com.yin.myproject.demo.concurrent.simulation.bank;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable {
	private ExecutorService service;
	private CustomerLine customers;
	private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
	private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
	private int adjustmentPeriod;

	public TellerManager(ExecutorService service, CustomerLine customers, int adjustmentPeriod) {
		this.service = service;
		this.customers = customers;
		this.adjustmentPeriod = adjustmentPeriod;
		Teller teller = new Teller(customers);
		service.execute(teller);
		workingTellers.add(teller);
	}

	public void adjustTellerNumber() {
		// 首先判断顾客的人数是否超过工作中收纳员人数的一倍，
		if (customers.size() / workingTellers.size() > 2) {
			// 然后再判断是否有干其他事情的工作人员 如果存在拉去服务顾客
			if (tellersDoingOtherThings.size() > 0) {
				Teller teller = tellersDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTellers.offer(teller);
				return;
			}
			// 如果不存在,新建收纳员去干活
			Teller teller = new Teller(customers);
			service.execute(teller);
			workingTellers.add(teller);
			return;
		}
		// 如果服务的客户队列足够小
		if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
			reassignOneTeller();
		}

		// 如果客户队列已经没了
		if (customers.size() == 0) {
			while (workingTellers.size() > 1) {
				reassignOneTeller();
			}
		}
	}

	private void reassignOneTeller() {
		Teller teller = workingTellers.poll();// 从工作的队列中删除第一个元素
		teller.doSomethingElse();// 使该元素去做其他事情
		tellersDoingOtherThings.offer(teller);// 做其他事情的队列加入该元素
	}

	public void run() {
		while (!Thread.interrupted()) {
			try {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(customers + "{ ");
				for (Teller teller : workingTellers) {
					System.out.print(teller.shortString() + " ");
				}
				System.out.println("}");
			} catch (InterruptedException e) {
				System.out.println(this + " interrupted");
			}
		}
		System.out.println(this + "terminating");
	}

	@Override
	public String toString() {
		return "TellerManager ";
	}

}
