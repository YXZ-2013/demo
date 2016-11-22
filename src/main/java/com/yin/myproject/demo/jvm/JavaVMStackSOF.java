package com.yin.myproject.demo.jvm;

/**
 * VM args: -Xss128K
 * Created by Administrator on 2016/11/21.
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
        }finally {
            System.out.println("stack length:" + oom.stackLength);
        }
    }
}
