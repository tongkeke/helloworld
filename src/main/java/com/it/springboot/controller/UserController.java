package com.it.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.it.springboot.bean.User;
import com.it.springboot.service.UserService;
import com.it.springboot.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: UserController
 * @Author: dawsontong
 * @Date: 2020/6/29 10:46
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private int age;

    @Autowired
    public DataSource dataSource;
    @Autowired
    UserService userServiceImpl;

    @GetMapping("/allUsers")
    public String getAllUsers(HttpServletRequest httpServletRequest){
        String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        logger.info("Request  ipAddr  is : "+ipAddr);
        logger.info("access api : /api/allUsers");
        logger.info(dataSource.getClass().toString());
        return "Hello World" +" : "+name +" "+age+"岁" +"\n\n"+JSON.toJSONString(userServiceImpl.queryAll());
    }
    @GetMapping("/getById")
    public String getUserById(@RequestParam("id") int id) {
        User user = userServiceImpl.queryById(id);
        return user.toString();
    }
}
