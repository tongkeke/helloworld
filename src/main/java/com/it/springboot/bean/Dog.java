package com.it.springboot.bean;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: Dog
 * @Author: dawsontong
 * @Date: 2020/5/6 16:48
 * @Description:
 */
//@Component
public class Dog {
    private String name;
    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
