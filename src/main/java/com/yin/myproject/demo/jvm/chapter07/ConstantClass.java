package com.yin.myproject.demo.jvm.chapter07;

/**
 * Created by Eason on 2017/1/5.
 */
public class ConstantClass {
    static {
        System.out.println("Constant init!");
    }

    public static final String HELLOWORLD = "Hello world";
}
