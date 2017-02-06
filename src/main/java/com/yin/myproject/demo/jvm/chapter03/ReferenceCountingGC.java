package com.yin.myproject.demo.jvm.chapter03;

/**
 * Created by Eason on 2017/1/9.
 * 从运行结果中可以清楚地看到GC日志中包含了垃圾回收动作，意味着虚拟就并没有因为这两个对象互相引用就不回收它们，这也从侧面
 * 说明虚拟机并不是通过引用计数算法来判断对象是否存活的
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        new ReferenceCountingGC().testGC();
    }
}
