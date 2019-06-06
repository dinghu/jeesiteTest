/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 亚马逊销售产品价格表Entity
 * @author hale
 * @version 2019-06-06
 */
public class OaProductAmazon extends DataEntity<OaProductAmazon> {
	
	private static final long serialVersionUID = 1L;
	private User create_by;		// 提交人
	private Date create_date;		// 提交时间
	private String sku;		// SKU
	private String name;		// 品名
	private String chengben;		// 成本
	private String maoli20;		// 20%毛利
	private String advise_price;		// 建议定价
	private String nitiaojia;		// 拟调价
	
	public OaProductAmazon() {
		super();
	}

	public OaProductAmazon(String id){
		super(id);
	}

	public User getCreate_by() {
		return create_by;
	}

	public void setCreate_by(User create_by) {
		this.create_by = create_by;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	@Length(min=0, max=255, message="SKU长度必须介于 0 和 255 之间")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Length(min=0, max=255, message="品名长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getChengben() {
		return chengben;
	}

	public void setChengben(String chengben) {
		this.chengben = chengben;
	}
	
	public String getMaoli20() {
		return maoli20;
	}

	public void setMaoli20(String maoli20) {
		this.maoli20 = maoli20;
	}
	
	public String getAdvise_price() {
		return advise_price;
	}

	public void setAdvise_price(String advise_price) {
		this.advise_price = advise_price;
	}
	
	public String getNitiaojia() {
		return nitiaojia;
	}

	public void setNitiaojia(String nitiaojia) {
		this.nitiaojia = nitiaojia;
	}
	
}