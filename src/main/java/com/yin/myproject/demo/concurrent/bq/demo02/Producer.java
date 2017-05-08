package com.yin.myproject.demo.concurrent.bq.demo02;

import com.yin.myproject.demo.concurrent.base.SleepDemo01;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinxunzhi on 2022/7/14
 */
public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;

    Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while(true){
            int num = new Random().nextInt(10);
            boolean offer = queue.offer(num);
            System.out.println(Thread.currentThread().getName() + "生产者线程往队列中添加元素:" + num + "添加结果" + offer);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
