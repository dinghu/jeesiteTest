package com.thinkgem.jeesite.modules.oa.entity;

import java.io.Serializable;
import java.util.Date;

public class OaWorkTaskLog implements Serializable {
    private Integer id;

    private Integer oaTaskId;

    private Integer opFromUid;

    private Integer opToUid;

    private Integer type;

    private String content;

    private Date opTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOaTaskId() {
        return oaTaskId;
    }

    public void setOaTaskId(Integer oaTaskId) {
        this.oaTaskId = oaTaskId;
    }

    public Integer getOpFromUid() {
        return opFromUid;
    }

    public void setOpFromUid(Integer opFromUid) {
        this.opFromUid = opFromUid;
    }

    public Integer getOpToUid() {
        return opToUid;
    }

    public void setOpToUid(Integer opToUid) {
        this.opToUid = opToUid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}