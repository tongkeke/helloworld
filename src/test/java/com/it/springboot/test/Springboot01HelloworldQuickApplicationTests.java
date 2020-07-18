package com.it.springboot.test;

import com.alibaba.fastjson.JSON;
import com.it.springboot.Springboot01HelloworldQuickApplication;
import com.it.springboot.bean.Department;
import com.it.springboot.bean.User;
import com.it.springboot.mapper.DepartmentMapper;
import com.it.springboot.mongo.MongoDBBean;
import com.it.springboot.mongo.MongoRepostory;
import com.it.springboot.mongo.MsgPayload;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Springboot01HelloworldQuickApplication.class})
public class Springboot01HelloworldQuickApplicationTests {
    private final static Logger log = LoggerFactory.getLogger(Springboot01HelloworldQuickApplicationTests.class);
    @Autowired
    public DataSource dataSource;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    MongoDBBean mongoDBBean;
    //@Autowired
    //MongoDataSourceProperties mongoDataSourceProperties;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    MongoRepostory mongoRepostory;

    @Test
    public void testMybatis() {
        try {
            System.out.println("++++++++"+dataSource.getClass());
            Connection connection = dataSource.getConnection();
            System.out.println("+++++++++"+connection.getClass());
            connection.close();
        } catch ( SQLException e ) {
            log.error(e.getMessage());
        }

        Department dept = departmentMapper.getDeptById(1);
        System.out.println("===========  "+dept.getId()+"  :  "+dept.getDepartmentName());



    }
    @Test
    public void testMongo(){
        MongoDatabase db = mongoDBBean.getDb();
        String dbName = db.getName();
        MongoIterable< String > strings = db.listCollectionNames();
        MongoCursor< String > iterator = strings.iterator();
        while ( iterator.hasNext() ){
            System.out.println("------  : "+iterator.next());
        }


        //db.createCollection("hello");
        //db.runCommand();


    }
    @Test
    public void testRabbitMq() {


    }

    @Test
    public void testMongoTemplate() {
        Set< String > collectionNames = mongoTemplate.getCollectionNames();
        Iterator< String > iterator = collectionNames.iterator();
        while ( iterator.hasNext() ){
            String next = iterator.next();
            System.out.println("collectname : "+next);
        }

        MongoCollection< Document > hello = mongoTemplate.getCollection("hello");
        //1.Document
        Document doc = new Document();
        doc.put("name", "hello world");
        hello.insertOne(doc);

        String collectionName = "hello";
        MsgPayload msgPayload = new MsgPayload();
        msgPayload.setContentId(UUID.randomUUID().toString());
        User user = new User();
        user.setId(1);
        user.setUserName("haha");
        user.setAge(20);
        user.setAddress("shanghai");
        user.setMobilePhone("12656565565665");
        msgPayload.setContentData(JSON.toJSONString(user));
        msgPayload.setCreateTime(System.currentTimeMillis());
        msgPayload.setDocType(User.class.getTypeName());
        msgPayload.setRemark("haha go home");
        //MsgPayload hello1 = mongoTemplate.save(msgPayload, "hello");

        /*List< MsgPayload > hello2 = mongoTemplate.find(new Query(), MsgPayload.class, "hello");
        long hello4 = mongoTemplate.count(new Query(), MsgPayload.class, "hello");
        List< MsgPayload > mappedResults = mongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(new Criteria()), Aggregation.sort(new Sort(Sort.Direction.DESC, "lastUpdateTime"))), "hello", MsgPayload.class).getMappedResults();

        Criteria criteria = Criteria.where("1").is("1").and("2").is("2");
        Query query = new Query().addCriteria(criteria);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, Update.update("remark", "hehe work"), collectionName);

        DeleteResult remove = mongoTemplate.remove(new Query(), MsgPayload.class, collectionName);*/

        for (int i = 0; i <5 ; i++) {
            mongoRepostory.save(msgPayload, collectionName);
        }
        Page< User > users = mongoRepostory.queryByCondition(msgPayload, new PageRequest(2, 2), collectionName);
        System.out.println("hello");
    }

}
