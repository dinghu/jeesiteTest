/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaProductAmazon;
import com.thinkgem.jeesite.modules.oa.dao.OaProductAmazonDao;

/**
 * 亚马逊销售产品价格表Service
 * @author hale
 * @version 2019-06-06
 */
@Service
@Transactional(readOnly = true)
public class OaProductAmazonService extends CrudService<OaProductAmazonDao, OaProductAmazon> {

	public OaProductAmazon get(String id) {
		return super.get(id);
	}
	
	public List<OaProductAmazon> findList(OaProductAmazon oaProductAmazon) {
		return super.findList(oaProductAmazon);
	}
	
	public Page<OaProductAmazon> findPage(Page<OaProductAmazon> page, OaProductAmazon oaProductAmazon) {
		return super.findPage(page, oaProductAmazon);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProductAmazon oaProductAmazon) {
		super.save(oaProductAmazon);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProductAmazon oaProductAmazon) {
		super.delete(oaProductAmazon);
	}
	
}