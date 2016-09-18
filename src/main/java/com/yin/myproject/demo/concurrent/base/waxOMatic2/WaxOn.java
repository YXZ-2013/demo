package com.yin.myproject.demo.concurrent.base.waxOMatic2;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car c){
		car = c;
	}
	
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		}catch(InterruptedException e){
			System.out.println("Exiting via interrupt");
		}finally{
			System.out.println("Ending Wax On task");
		}
	}
	
}
