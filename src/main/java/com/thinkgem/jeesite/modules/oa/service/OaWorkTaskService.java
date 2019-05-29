package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.mysql.FilterRule;
import com.thinkgem.jeesite.common.mysql.FilterRuleBuilder;
import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.core.BaseService;
import com.thinkgem.jeesite.modules.oa.dao.OaWorkTaskDao;
import com.thinkgem.jeesite.modules.oa.dao.OaWorkTaskLogDao;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OaWorkTaskService extends BaseService<OaWorkTaskDao, OaWorkTask> {
    @Autowired
    private OaWorkTaskLogDao oaWorkTaskLogDao;


    public List<OaWorkTaskLog> getWorkTaskLog(Integer OaWorkTaskId) {
        PageQuery pageQuery = new PageQuery("op_time", "desc", 0);
        List<FilterRule> rules = FilterRuleBuilder.newBuilder().key("oa_task_id").eq().value(OaWorkTaskId).build();
        return oaWorkTaskLogDao.findByPage(rules,pageQuery);
    }

    public int saveOaWorkTaskLog(OaWorkTaskLog oaWorkTaskLog) {
        return oaWorkTaskLogDao.insertSelective(oaWorkTaskLog);
    }
}
