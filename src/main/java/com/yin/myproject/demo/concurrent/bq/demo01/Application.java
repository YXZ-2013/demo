package com.yin.myproject.demo.concurrent.bq.demo01;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by yinxunzhi on 2022/7/14
 */
public class Application {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

    }
}
