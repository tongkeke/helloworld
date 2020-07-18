package com.it.springboot.serviceImpl;

import com.it.springboot.bean.User;
import com.it.springboot.mapper.UserMapper;
import com.it.springboot.mongo.MongoRepostory;
import com.it.springboot.mongo.MsgPayload;
import com.it.springboot.service.UserService;
import com.it.springboot.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private MongoRepostory mongoRepostory;
 
    @Resource
    private UserMapper userMapper;
 
    @Override
    public List<User > queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public User queryById(int id) {
        int i = 1/0;
        try {
            mongoRepostory.save(new MsgPayload(), "");
        } catch ( Exception e ) {
            log.error(ExceptionUtil.getStackTrace(e));
        }
        return userMapper.queryById(id);
    }


}