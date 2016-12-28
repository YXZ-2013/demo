package com.yin.myproject.demo.serializable;

import java.io.*;

/**
 * Created by Eason on 2016/12/28.
 */
public class Person2 implements Serializable {

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

    private void writeObject(ObjectOutputStream oos){
        try {
            oos.defaultWriteObject();
            oos.writeObject(this.age);
            System.out.println(this);
            System.out.println("序列化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream ois){
        try {
            ois.defaultReadObject();
            this.setAge(((Integer) ois.readObject()).intValue() - 1);
            System.out.println("反序列化成功");
            System.out.println(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Person2 person = new Person2();
        person.setName("zhang san");
        person.setAge(20);
        System.out.println(person);
        try {
            FileOutputStream fos = new FileOutputStream("D:\\temp\\test\\person3.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis =  new FileInputStream("D:\\temp\\test\\person3.obj");
            ObjectInputStream is = new ObjectInputStream(fis);
            is.readObject();
            fis.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
