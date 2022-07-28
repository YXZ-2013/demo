package com.yin.myproject.demo.function;

/**
 * Created by yinxunzhi on 2022/7/28
 */
public class Apple {

    public Apple() {
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    private String color;

    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }




}
