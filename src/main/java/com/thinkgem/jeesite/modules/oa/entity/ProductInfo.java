package com.thinkgem.jeesite.modules.oa.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo implements Serializable {
    private Integer id;

    private Integer pid;

    private String createUid;

    private String sku;

    private String name;

    private String keywords;

    private String images;

    private String color;

    private Integer yangpin;

    private String issametoyangpin;

    private String packingRequirements;

    private String warehousePackagingRequirements;

    private String checkPoints;

    private String singlePackingway;

    private String saleGroupway;

    private String strengthsWeaknesses;

    private String kandengAdvises;

    private String functionParams;

    private String parts;

    private String link;

    private String note;

    private String imageProductionRequirements;

    private String others;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYangpin() {
        return yangpin;
    }

    public void setYangpin(Integer yangpin) {
        this.yangpin = yangpin;
    }

    public String getIssametoyangpin() {
        return issametoyangpin;
    }

    public void setIssametoyangpin(String issametoyangpin) {
        this.issametoyangpin = issametoyangpin;
    }

    public String getPackingRequirements() {
        return packingRequirements;
    }

    public void setPackingRequirements(String packingRequirements) {
        this.packingRequirements = packingRequirements;
    }

    public String getWarehousePackagingRequirements() {
        return warehousePackagingRequirements;
    }

    public void setWarehousePackagingRequirements(String warehousePackagingRequirements) {
        this.warehousePackagingRequirements = warehousePackagingRequirements;
    }

    public String getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(String checkPoints) {
        this.checkPoints = checkPoints;
    }

    public String getSinglePackingway() {
        return singlePackingway;
    }

    public void setSinglePackingway(String singlePackingway) {
        this.singlePackingway = singlePackingway;
    }

    public String getSaleGroupway() {
        return saleGroupway;
    }

    public void setSaleGroupway(String saleGroupway) {
        this.saleGroupway = saleGroupway;
    }

    public String getStrengthsWeaknesses() {
        return strengthsWeaknesses;
    }

    public void setStrengthsWeaknesses(String strengthsWeaknesses) {
        this.strengthsWeaknesses = strengthsWeaknesses;
    }

    public String getKandengAdvises() {
        return kandengAdvises;
    }

    public void setKandengAdvises(String kandengAdvises) {
        this.kandengAdvises = kandengAdvises;
    }

    public String getFunctionParams() {
        return functionParams;
    }

    public void setFunctionParams(String functionParams) {
        this.functionParams = functionParams;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageProductionRequirements() {
        return imageProductionRequirements;
    }

    public void setImageProductionRequirements(String imageProductionRequirements) {
        this.imageProductionRequirements = imageProductionRequirements;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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