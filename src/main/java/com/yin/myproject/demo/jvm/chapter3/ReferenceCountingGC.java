package com.yin.myproject.demo.jvm.chapter3;

/**
 * Created by Eason on 2016/12/5.
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 *1024;
    /**
     * 只是为了占点内存
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //假设在这里发生GC，那么objA与objB是否会被回收
        System.gc();



    }
}
