package com.it.springboot.mongo;

import com.alibaba.fastjson.JSON;
import com.it.springboot.bean.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020, xxx有限公司
 *
 * @FileName: MongoRepostory
 * @Author: dawsontong
 * @Date: 2020/7/14 23:27
 * @Description:
 */
@Component
public class MongoRepostory implements IMongoRepostory<User>{
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    GridFsTemplate gridFsTemplate;

    //测通
    @Override
    public void save(MsgPayload msgPayload,String collectionName) {
        MsgPayload hello1 = mongoTemplate.save(msgPayload, collectionName);
    }

    @Override
    public void update(MsgPayload msgPayload,String collectionName){
        Criteria criteria = Criteria.where("1").is("1").and("2").is("2");
        Query query = new Query().addCriteria(criteria);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, Update.update("remark", "hehe work"), collectionName);

    }

    @Override
    public User queryOneByCondition(MsgPayload msgPayload, String collectionName) {
        return null;
    }

    //测通
    @Override
    public Page<User> queryByCondition(MsgPayload msgPayload, Pageable page, String collectionName){
        Query query = new Query();

        long totalCount = mongoTemplate.count(new Query(), MsgPayload.class, collectionName);
        query.with(new Sort(Sort.Direction.DESC,"lastUpdateTime"));
        //page.getPageNumber()，第几页，从0开始
        //page.getPageSize()，定义一页显示的记录条数
        query.skip(page.getPageSize() * page.getPageNumber());
        query.limit(page.getPageSize());
        List< MsgPayload > mappedResults = mongoTemplate.find(query, MsgPayload.class, collectionName);
        List< MsgPayload > mappedResults2 = mongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(new Criteria()), Aggregation.sort(new Sort(Sort.Direction.DESC, "lastUpdateTime"))), collectionName, MsgPayload.class).getMappedResults();
        //数据转换
        List<User> userList = new ArrayList<>();
        for (MsgPayload msgPayload1:mappedResults) {
            userList.add(getContentFromPayload(msgPayload1));
        }
        return new PageImpl<>(userList,page, totalCount);
    }

    @Override
    public void remove(MsgPayload msgPayload,String collectionName){
        DeleteResult remove = mongoTemplate.remove(new Query(), MsgPayload.class, collectionName);

    }

    @Override
    public User getContentFromPayload(MsgPayload msgPayload) {
        User user = JSON.parseObject(msgPayload.getContentData(), User.class);
        return user;
    }


}
