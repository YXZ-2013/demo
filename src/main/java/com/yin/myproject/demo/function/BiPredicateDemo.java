package com.yin.myproject.demo.function;

import java.util.function.BiPredicate;

/**
 * Created by yinxunzhi on 2022/7/28
 */
public class BiPredicateDemo {
    public static void main(String[] args) {
        BiPredicate<String, Integer> filter = (x, y) -> {
            return x.length() == y;
        };

        boolean result = filter.test("mkyong", 6);
        System.out.println(result);

        boolean result2 = filter.test("java", 10);
        System.out.println(result2);
    }
}
