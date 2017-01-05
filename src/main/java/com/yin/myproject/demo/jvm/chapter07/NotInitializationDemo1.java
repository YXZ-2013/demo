package com.yin.myproject.demo.jvm.chapter07;

/**
 * Created by Eason on 2017/1/5.
 *
 * 对于静态字段,只有直接定义这个字段的类才会被初始化,因此,通过其子类来引用其父类中定义的静态字段，只会触发父类的初始化而不会触发其子类的初始化。
 */
public class NotInitializationDemo1 {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
