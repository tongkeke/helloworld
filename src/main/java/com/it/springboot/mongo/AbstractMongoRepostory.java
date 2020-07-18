package com.it.springboot.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: MongoRepostory
 * @Author: dawsontong
 * @Date: 2020/7/14 23:27
 * @Description:
 */
public class AbstractMongoRepostory {
    private static final Logger log = LoggerFactory.getLogger(AbstractMongoRepostory.class);
    //定义业务相关的表
    protected String tmpCollection;
    protected String historyCollection;

    protected MongoTemplate mongoTemplate;
    AbstractMongoRepostory(){

    }






}
