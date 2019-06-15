/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户售后反馈Entity
 * @author hale
 * @version 2019-06-15
 */
public class OaFeedback extends DataEntity<OaFeedback> {
	
	private static final long serialVersionUID = 1L;
	private Office officeInfo;		// 部门
	private String sku;		// sku
	private String link;		// 链接
	private String content;		// 反馈内容
	private String buyerinfo;		// 买家信息
	private String images;		// 可放图片
	private String dealResult;		// 处理结果
	private String reson;		// 原因
	private String linkmaybe;		// 可放连接
	private User solveBy;		// 信息处理人
	private String dealWay;		// 处理办法
	
	public OaFeedback() {
		super();
	}

	public OaFeedback(String id){
		super(id);
	}

	public Office getOfficeInfo() {
		return officeInfo;
	}

	public void setOfficeInfo(Office officeInfo) {
		this.officeInfo = officeInfo;
	}
	
	@Length(min=0, max=32, message="sku长度必须介于 0 和 32 之间")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Length(min=0, max=128, message="链接长度必须介于 0 和 128 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=512, message="反馈内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=128, message="买家信息长度必须介于 0 和 128 之间")
	public String getBuyerinfo() {
		return buyerinfo;
	}

	public void setBuyerinfo(String buyerinfo) {
		this.buyerinfo = buyerinfo;
	}
	
	@Length(min=0, max=128, message="可放图片长度必须介于 0 和 128 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	@Length(min=0, max=11, message="处理结果长度必须介于 0 和 11 之间")
	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	
	@Length(min=0, max=128, message="原因长度必须介于 0 和 128 之间")
	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}
	
	@Length(min=0, max=128, message="可放连接长度必须介于 0 和 128 之间")
	public String getLinkmaybe() {
		return linkmaybe;
	}

	public void setLinkmaybe(String linkmaybe) {
		this.linkmaybe = linkmaybe;
	}
	
	public User getSolveBy() {
		return solveBy;
	}

	public void setSolveBy(User solveBy) {
		this.solveBy = solveBy;
	}
	
	@Length(min=0, max=128, message="处理办法长度必须介于 0 和 128 之间")
	public String getDealWay() {
		return dealWay;
	}

	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
	
}