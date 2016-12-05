package com.yin.myproject.demo.jvm.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by Eason on 2016/12/5.
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("Yes,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为Finalized方法优先级很低 暂停0.5秒 以等待它执行
        TimeUnit.SECONDS.sleep(1);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,i am dead :(");
        }

        SAVE_HOOK = null;
        System.gc();
        //因为Finalized方法优先级很低 暂停0.5秒 以等待它执行
        TimeUnit.SECONDS.sleep(1);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,i am dead :(");
        }

    }

}
