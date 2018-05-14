package com.lyun.www.documentshow.bean;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Weijie Qiu on 2018/3/25.
 */

/**
 * 功能: 存储文档的信息
 */
public class Document {
    private UUID mId;
    private String mName;
    private Date mDate;

    public Document(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
