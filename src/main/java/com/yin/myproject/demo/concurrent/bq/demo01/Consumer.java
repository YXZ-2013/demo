package com.yin.myproject.demo.concurrent.bq.demo01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinxunzhi on 2022/7/14
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            Integer poll = queue.poll();
            System.out.println(Thread.currentThread().getName() + "生产者线程往队列中获取元素:" + poll);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
