package com.yin.myproject.demo.serializable;

/**
 * Created by Eason on 2016/12/28.
 */
public class Person5 {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person5{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person5(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person5() {
    }
}
