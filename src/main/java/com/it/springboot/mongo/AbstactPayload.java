package com.it.springboot.mongo;

/**
 * Copyright (C), 2018-2020, 艾融软件有限公司
 *
 * @FileName: AbstactPayload
 * @Author: dawsontong
 * @Date: 2020/7/15 14:56
 * @Description:
 */
public class AbstactPayload<E> implements IPayload{
    private String creator;
    private long createTime;
    private String reviser;
    private long lastUpdateTime;
    private String remark;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getReviser() {
        return reviser;
    }

    public void setReviser(String reviser) {
        this.reviser = reviser;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
