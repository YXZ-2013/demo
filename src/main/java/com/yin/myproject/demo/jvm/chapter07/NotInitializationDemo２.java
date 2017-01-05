package com.yin.myproject.demo.jvm.chapter07;

/**
 * Created by Eason on 2017/1/5.
 *
 * 通过数组来引用类，不会触发类的初始化
 */
public class NotInitializationDemo２ {
    public static void main(String[] args) {
       SuperClass[] arr = new SuperClass[10];
    }
}
