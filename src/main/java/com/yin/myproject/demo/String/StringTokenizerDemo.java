package com.yin.myproject.demo.String;

import java.util.StringTokenizer;

/**
 * Created by XunzhiYin on 2016/7/21 14:16.
 */
public class StringTokenizerDemo {
    public static void main(String[] args) {
        new StringTokenizerDemo().demo1();
        new StringTokenizerDemo().demo2();
        new StringTokenizerDemo().demo3();
    }

    public void demo1() {
        String content = "Both sides reached consensus on market access and the text of free trade agreement, " +
                "making a substantive progress and a basic completion of the FTA negotiation.";
        StringTokenizer st = new StringTokenizer(content);
        System.out.println("分割后Token的个数-->" + st.countTokens());
        int i = 1;
        while (st.hasMoreElements()) {
            String token = st.nextToken();
            System.out.print(i + "." + token + " ");
            i++;
        }
    }

    public void demo2() {
        String content = "https://blog.jetbrains.com/idea/2009/04/global-unused-declaration-inspection/";
        StringTokenizer st = new StringTokenizer(content, "/");
        System.out.println("分割后Token的个数-->" + st.countTokens());
        int i = 1;
        while (st.hasMoreElements()) {
            String token = st.nextToken();
            System.out.print(i + "." + token + " ");
            i++;
        }
    }

    public void demo3() {
        String content = "https://blog.jetbrains.com/idea/2009/04/global-unused-declaration-inspection/";
        StringTokenizer st = new StringTokenizer(content, "/", true);
        System.out.println("分割后Token的个数-->" + st.countTokens());
        int i = 1;
        while (st.hasMoreElements()) {
            String token = st.nextToken();
            System.out.print(i + "." + token + " ");
            i++;
        }
    }
}
