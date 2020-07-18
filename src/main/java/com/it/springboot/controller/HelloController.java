package com.it.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.it.springboot.service.UserService;
import com.it.springboot.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: HelloController
 * @Author: dawsontong
 * @Date: 2020/5/6 16:03
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private int age;

    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userServiceImpl;

    @GetMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest){
        logger.debug(dataSource.getClass().toString());
        try {
            dataSource.getConnection();
        } catch ( SQLException e ) {
            logger.error(e.getMessage());
        }
        String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        logger.info("Request  ipAddr  is : "+ipAddr);
        logger.info("access api : /api/hello");
        logger.info(dataSource.getClass().toString());
        logger.info("Request  ipAddr  is : "+ipAddr +"\nHello World" +" : "+name +" "+age+"岁" +"\n"+JSON.toJSONString(userServiceImpl.queryAll()));
        return "Request  ipAddr  is : "+ipAddr +"  ====    "+"Hello World" +" : "+name +" "+age+"岁" +"  ====    "+JSON.toJSONString(userServiceImpl.queryAll());
    }

}
