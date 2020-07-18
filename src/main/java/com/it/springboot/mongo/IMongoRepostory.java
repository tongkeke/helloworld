package com.it.springboot.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMongoRepostory<T> {
    void save(MsgPayload msgPayload,String collectionName);
    void update(MsgPayload msgPayload,String collectionName);
    T queryOneByCondition(MsgPayload msgPayload,String collectionName);
    Page<T> queryByCondition(MsgPayload msgPayload, Pageable page,String collectionName);
    void remove(MsgPayload msgPayload,String collectionName);

    T getContentFromPayload(MsgPayload msgPayload);

}
