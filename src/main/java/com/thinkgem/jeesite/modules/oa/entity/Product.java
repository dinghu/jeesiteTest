package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private Integer id;

    private String createUid;
    @ExcelField(title = "SKU", align = 2, sort = 1)
    private String sku;
    @ExcelField(title = "品名", align = 2, sort = 2)
    private String name;
    @ExcelField(title = "装箱体积（m³）", align = 2, sort = 3)
    private Integer packingVolume;

    private Integer packingAmout;

    @ExcelField(title = "毛重（g）", align = 2, sort = 4)
    private Double grossWeight;
    @ExcelField(title = "单个包装尺寸（cm）", align = 2, sort = 5)
    private Integer singlePackingSize;
    @ExcelField(title = "单价", align = 2, sort = 6)
    private Double price;

    @ExcelField(title = "国内运费", align = 2, sort = 7)
    private Double domesticReight;

    @ExcelField(title = "包装费", align = 2, sort = 8)
    private Double packingPrice;

    @ExcelField(title = "国际运费", align = 2, sort = 9)
    private Double internationalShipping;
    @ExcelField(title = "关税税率", align = 2, sort = 10)
    private Double rate;

    private String hsCode;

    private String tariff;

    @ExcelField(title = "当地运费", align = 2, sort = 11)
    private Double localFreight;

    @ExcelField(title = "Ebay成本", align = 2, sort = 12)
    private Double ebayCost;

    @ExcelField(title = "OH 20%毛利价格", align = 2, sort = 13)
    private Double ebay20pPrice;

    private Double amazonCost;

    private Double amazon20pPrice;

    @ExcelField(title = "实际定价", align = 2, sort = 14)
    private Double advicePrice;

    @ExcelField(title = "拟调价", align = 2, sort = 15)
    private Double nitiaojia;

    private String manufacturerLink;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(Integer packingVolume) {
        this.packingVolume = packingVolume;
    }

    public Integer getPackingAmout() {
        return packingAmout;
    }

    public void setPackingAmout(Integer packingAmout) {
        this.packingAmout = packingAmout;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Integer getSinglePackingSize() {
        return singlePackingSize;
    }

    public void setSinglePackingSize(Integer singlePackingSize) {
        this.singlePackingSize = singlePackingSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDomesticReight() {
        return domesticReight;
    }

    public void setDomesticReight(Double domesticReight) {
        this.domesticReight = domesticReight;
    }

    public Double getPackingPrice() {
        return packingPrice;
    }

    public void setPackingPrice(Double packingPrice) {
        this.packingPrice = packingPrice;
    }

    public Double getInternationalShipping() {
        return internationalShipping;
    }

    public void setInternationalShipping(Double internationalShipping) {
        this.internationalShipping = internationalShipping;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public Double getLocalFreight() {
        return localFreight;
    }

    public void setLocalFreight(Double localFreight) {
        this.localFreight = localFreight;
    }

    public Double getEbayCost() {
        return ebayCost;
    }

    public void setEbayCost(Double ebayCost) {
        this.ebayCost = ebayCost;
    }

    public Double getEbay20pPrice() {
        return ebay20pPrice;
    }

    public void setEbay20pPrice(Double ebay20pPrice) {
        this.ebay20pPrice = ebay20pPrice;
    }

    public Double getAmazonCost() {
        return amazonCost;
    }

    public void setAmazonCost(Double amazonCost) {
        this.amazonCost = amazonCost;
    }

    public Double getAmazon20pPrice() {
        return amazon20pPrice;
    }

    public void setAmazon20pPrice(Double amazon20pPrice) {
        this.amazon20pPrice = amazon20pPrice;
    }

    public Double getAdvicePrice() {
        return advicePrice;
    }

    public void setAdvicePrice(Double advicePrice) {
        this.advicePrice = advicePrice;
    }

    public Double getNitiaojia() {
        return nitiaojia;
    }

    public void setNitiaojia(Double nitiaojia) {
        this.nitiaojia = nitiaojia;
    }

    public String getManufacturerLink() {
        return manufacturerLink;
    }

    public void setManufacturerLink(String manufacturerLink) {
        this.manufacturerLink = manufacturerLink;
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