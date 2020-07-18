package com.it.springboot.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: MsgPayload
 * @Author: dawsontong
 * @Date: 2020/7/15 14:53
 * @Description:
 */
//定义数据模板
public class MsgPayload<E> extends AbstactPayload implements Serializable,Comparable<MsgPayload> {
    private static final Logger log = LoggerFactory.getLogger(MsgPayload.class);
    private static final long serialVersionUID = 1l;
    private String payloadId;
    private String contentId;
    private String contentData;
    private String docType;
    private E entity;
    private List<E> entityLists;

    public MsgPayload() {
    }

    public String getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(String payloadId) {
        this.payloadId = payloadId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List< E > getEntityLists() {
        return entityLists;
    }

    public void setEntityLists(List< E > entityLists) {
        this.entityLists = entityLists;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    @Override
    public int compareTo(MsgPayload o) {
        return Math.toIntExact(o.getLastUpdateTime() - this.getLastUpdateTime());
    }
}
