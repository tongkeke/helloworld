package com.it.springboot.bean;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: Department
 * @Author: dawsontong
 * @Date: 2020/6/28 14:36
 * @Description:
 */
public class Department {
    private int id;
    private String departmentName;

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
