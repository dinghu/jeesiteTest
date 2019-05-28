package com.thinkgem.jeesite.modules.oa.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaWorkTask implements Serializable {
    private Integer id;

    private String title;

    private Integer createUid;

    private String content;

    private Integer status;

    private String files;

    private Date createTime;

    private Date updateTime;

    private boolean isSelf;        // 是否只查询自己的通知

    private Page<OaWorkTask> page;

    private List<OaWorkTaskLog> oaWorkTaskLogList = new ArrayList<OaWorkTaskLog>();

    public List<OaWorkTaskLog> getOaWorkTaskLogList() {
        return oaWorkTaskLogList;
    }

    public void setOaWorkTaskLogList(List<OaWorkTaskLog> oaWorkTaskLogList) {
        this.oaWorkTaskLogList = oaWorkTaskLogList;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public Page<OaWorkTask> getPage() {
        return page;
    }

    public void setPage(Page<OaWorkTask> page) {
        this.page = page;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}