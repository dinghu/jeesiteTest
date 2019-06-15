/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaFeedback;

/**
 * 用户售后反馈DAO接口
 * @author hale
 * @version 2019-06-15
 */
@MyBatisDao
public interface OaFeedbackDao extends CrudDao<OaFeedback> {
	
}