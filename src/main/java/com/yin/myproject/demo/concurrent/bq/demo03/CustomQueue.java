package com.yin.myproject.demo.concurrent.bq.demo03;

import java.util.NoSuchElementException;

/**
 * Created by yinxunzhi on 2022/7/22
 */
public interface CustomQueue<E> {

    /**
     * 向队列中添加元素
     *
     * @param e 向队列中添加的元素
     * @return 当添加成功后返回true
     * @throws IllegalStateException    – if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException       – if the class of the specified element prevents it from being added to this queue
     * @throws NullPointerException     – if the specified element is null and this queue does not permit null elements
     * @throws IllegalArgumentException – if some property of this element prevents it from being added to this queue
     */
    boolean add(E e);

    /**
     * 向队列中添加元素
     *
     * @param e 向队列中添加的元素
     * @return 当添加成功后返回true, 反之false
     * @throws ClassCastException       if the class of the specified element prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element prevents it from being added to this queue
     */
    boolean offer(E e);

    /**
     * 获取并移除队列头部的元素
     *
     * @return 队列头部的元素
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();


}
