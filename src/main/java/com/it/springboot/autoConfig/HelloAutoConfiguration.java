package com.it.springboot.autoConfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020,
 *
 * @FileName: HelloConfiguration
 * @Author: dawsontong
 * @Date: 2020/7/14 10:29
 * @Description:
 */

@Configuration
@EnableConfigurationProperties({MongoClientOptionProperties.class})
public class HelloAutoConfiguration {

    //private MongoClientOptionProperties properties;

    /*
    * Springboot会拿这个MongoDbFactory工厂bean来new一个MongoTemplate，
    * 在MongoDbFactoryDependentConfiguration类下可以看到SpringBoot帮你做得事
    * 创建mongo库
    * */
    @Bean
    public MongoDbFactory mongoDbFactory(MongoClientOptionProperties properties) {
        //创建客户端参数
        MongoClientOptions options =MongoClientOptions.builder()
                .connectTimeout(properties.getConnectionTimeoutMs())
                .socketTimeout(properties.getReadTimeoutMs()).applicationName(properties.getClientName())
                .heartbeatConnectTimeout(properties.getHeartbeatConnectionTimeoutMs())
                .heartbeatSocketTimeout(properties.getHeartbeatReadTimeoutMs())
                .heartbeatFrequency(properties.getHeartbeatFrequencyMs())
                .minHeartbeatFrequency(properties.getMinHeartbeatFrequencyMs())
                .maxConnectionIdleTime(properties.getConnectionMaxIdleTimeMs())
                .maxConnectionLifeTime(properties.getConnectionMaxLifeTimeMs())
                .maxWaitTime(properties.getPoolMaxWaitTimeMs())
                .connectionsPerHost(properties.getConnectionsPerHost())
                .threadsAllowedToBlockForConnectionMultiplier(
                        properties.getThreadsAllowedToBlockForConnectionMultiplier())
                .minConnectionsPerHost(properties.getMinConnectionsPerHost()).build();
        //创建客户端和Factory
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String address : properties.getAddress()) {
            String[] hostAndPort = address.split(":");
            String host = hostAndPort[0];
            Integer port = Integer.parseInt(hostAndPort[1]);
            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
            //创建认证客户端
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(properties.getUsername(),
                    properties.getAuthenticationDatabase() != null ? properties.getAuthenticationDatabase() : properties.getDatabase(),
                    properties.getPassword().toCharArray());
        MongoClient  mongoClient = new MongoClient(serverAddresses, mongoCredential, options);
        return new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
    }



















}
