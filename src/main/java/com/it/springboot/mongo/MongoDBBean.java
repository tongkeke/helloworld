package com.it.springboot.mongo;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

@Component
public class MongoDBBean {

	private final MongoDbFactory mongoDbFactory;

	@Autowired
	public MongoDBBean(MongoDbFactory mongoDbFactory) {
		this.mongoDbFactory = mongoDbFactory;
	}

	public MongoDatabase getDb() {
		MongoDatabase mongoDatabase = mongoDbFactory.getDb();// ...
		return mongoDatabase;
	}

}