package com.yin.myproject.demo.serializable;

import java.io.Serializable;

/**
 * Created by Eason on 2016/12/28.
 */
public class Person1 implements Serializable {

    private String name;
    private transient Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public static void main(String[] args) throws ClassNotFoundException {
        Person1 person = new Person1();
        person.setName("zhang san");
        person.setAge(20);
        System.out.println(person);
        Serial.writeObject(person, "D:\\temp\\test\\person1.obj");


        person = (Person1) Serial.readObject("D:\\temp\\test\\person1.obj");
        if (person != null) {
            System.out.println(person);
        }
    }

}
