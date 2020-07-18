package com.it.springboot.mapper;

import com.it.springboot.bean.User;

import java.util.List;

public interface UserMapper {
 
    public List<User > queryAll();

    User queryById(int id);
}