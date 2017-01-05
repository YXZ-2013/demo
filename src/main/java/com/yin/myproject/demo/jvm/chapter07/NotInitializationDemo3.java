package com.yin.myproject.demo.jvm.chapter07;

/**
 * Created by Eason on 2017/1/5.
 *
 * 常量在编译阶段会存入调用类的的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化.
 * Java源码中引入了Constant类中的常量HELLOWORLD，但是在编译阶段将此常量的值存储到NotInitializationDemo3类的常量池中,对常量
 * ConstantClass.HELLOWORLD的引用转化为NotInitializationDemo3类对自身常量池的引用
 */
public class NotInitializationDemo3 {
    public static void main(String[] args) {
        System.out.println(ConstantClass.HELLOWORLD);
    }
}
