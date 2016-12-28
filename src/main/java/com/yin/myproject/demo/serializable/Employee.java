package com.yin.myproject.demo.serializable;

import java.io.Serializable;

/**
 * Created by Eason on 2016/12/28.
 */
public class Employee extends Person5 implements Serializable{


    public Employee(String name, int age) {
        super(name, age);
    }

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Zhang San", 30);
        emp.setCompanyName("XXX");
        Serial.writeObject(emp,"D:\\temp\\test\\employee.obj");

        emp = (Employee) Serial.readObject("D:\\temp\\test\\employee.obj");
        if(emp!=null){
            System.out.println(emp);
        }
    }
}


