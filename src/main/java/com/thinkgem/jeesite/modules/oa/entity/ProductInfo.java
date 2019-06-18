package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo implements Serializable {
    private Integer id;

    private Integer pid;

    private String createUid;

    @ExcelField(title = "提交人", align = 2, sort = 1, value = "createUser.name")
    private User createUser;
    @ExcelField(title = "提交时间", align = 2, sort = 3, value = "")
    private Date createTime;
    @ExcelField(title = "SKU", align = 2, sort = 2)
    private String sku;
    @ExcelField(title = "品名", align = 2, sort = 4)
    private String name;
    @ExcelField(title = "关键词", align = 2, sort = 5)
    private String keywords;

    private String images;
    @ExcelField(title = "颜色", align = 2, sort = 6)
    private String color;

    @ExcelField(title = "样品", align = 2, sort = 7, value = "")
    private Integer yangpin;

    @ExcelField(title = "样品和大货是否一致", align = 2, sort = 8, value = "")
    private String issametoyangpin;

    @ExcelField(title = "厂家包装要求", align = 2, sort = 9, value = "")
    private String packingRequirements;

    @ExcelField(title = "仓库包装要求", align = 2, sort = 10, value = "")
    private String warehousePackagingRequirements;
    @ExcelField(title = "验货注意事项", align = 2, sort = 11, value = "")
    private String checkPoints;
    @ExcelField(title = "单个包装方式", align = 2, sort = 12, value = "")
    private String singlePackingway;
    @ExcelField(title = "销售组合方式", align = 2, sort = 13, value = "")
    private String saleGroupway;

    @ExcelField(title = "产品特点及优缺点", align = 2, sort = 14, value = "")
    private String strengthsWeaknesses;

    @ExcelField(title = "产品刊登建议", align = 2, sort = 15, value = "")
    private String kandengAdvises;
    @ExcelField(title = "产品功能及参数", align = 2, sort = 16, value = "")
    private String functionParams;
    @ExcelField(title = "产品配件", align = 2, sort = 17, value = "")
    private String parts;
    @ExcelField(title = "产品主链接", align = 2, sort = 18, value = "")
    private String link;
    @ExcelField(title = "备注", align = 2, sort = 19, value = "")
    private String note;
    @ExcelField(title = "图片制作要求", align = 20, sort = 3, value = "")
    private String imageProductionRequirements;
    @ExcelField(title = "产品功能及参数", align = 21, sort = 3, value = "")
    private String others;

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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
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