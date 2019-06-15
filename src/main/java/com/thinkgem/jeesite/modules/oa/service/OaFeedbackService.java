/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaFeedback;
import com.thinkgem.jeesite.modules.oa.dao.OaFeedbackDao;

/**
 * 用户售后反馈Service
 * @author hale
 * @version 2019-06-15
 */
@Service
@Transactional(readOnly = true)
public class OaFeedbackService extends CrudService<OaFeedbackDao, OaFeedback> {

	public OaFeedback get(String id) {
		return super.get(id);
	}
	
	public List<OaFeedback> findList(OaFeedback oaFeedback) {
		return super.findList(oaFeedback);
	}
	
	public Page<OaFeedback> findPage(Page<OaFeedback> page, OaFeedback oaFeedback) {
		return super.findPage(page, oaFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(OaFeedback oaFeedback) {
		super.save(oaFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaFeedback oaFeedback) {
		super.delete(oaFeedback);
	}
	
}