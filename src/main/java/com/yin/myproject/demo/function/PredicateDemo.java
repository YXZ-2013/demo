package com.yin.myproject.demo.function;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yinxunzhi on 2022/7/28
 */
public class PredicateDemo {

    private static List<Apple> APPLES = Lists.newArrayList();

    static {
        APPLES.add(new Apple("green", 180));
        APPLES.add(new Apple("green", 180));
        APPLES.add(new Apple("red", 180));
        APPLES.add(new Apple("green", 180));
        APPLES.add(new Apple("origin", 180));
        APPLES.add(new Apple("green", 180));
        APPLES.add(new Apple("green", 180));
        APPLES.add(new Apple("green", 120));
        APPLES.add(new Apple("green", 120));
        APPLES.add(new Apple("green", 120));
        APPLES.add(new Apple("green", 120));
        APPLES.add(new Apple("green", 120));

    }

    public List<Apple> filterGreenApples() {
        List<Apple> result = Lists.newArrayList();
        for (Apple apple : APPLES) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }


    public List<Apple> filterApples(Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : APPLES) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
