package com.it.springboot.service;

import com.it.springboot.bean.User;

import java.util.List;

public interface UserService{
 
    /**
     * 查詢用戶信息列表
     * @return
     */
    public List<User > queryAll();

    public User queryById(int id);
}