package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;

import java.io.Serializable;
import java.util.Date;

public class OaWorkTaskLog implements Serializable {
    private Integer id;

    private String oaTaskId;

    private String opFromUid;

    private String opToUid;

    private Integer type;

    private String content;

    private String name;

    private String mark;

    private Date opTime;

    private User fromUser;

    private User toUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOaTaskId() {
        return oaTaskId;
    }

    public void setOaTaskId(String oaTaskId) {
        this.oaTaskId = oaTaskId;
    }

    public String getOpFromUid() {
        return opFromUid;
    }

    public void setOpFromUid(String opFromUid) {
        this.opFromUid = opFromUid;
    }

    public String getOpToUid() {
        return opToUid;
    }

    public void setOpToUid(String opToUid) {
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