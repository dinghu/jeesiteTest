package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.mysql.FilterRule;
import com.thinkgem.jeesite.common.mysql.FilterRuleBuilder;
import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.core.BaseService;
import com.thinkgem.jeesite.core.StringKeyBaseService;
import com.thinkgem.jeesite.modules.oa.dao.OaWorkTaskDao;
import com.thinkgem.jeesite.modules.oa.dao.OaWorkTaskLogDao;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OaWorkTaskService extends StringKeyBaseService<OaWorkTaskDao, OaWorkTask> {
    @Autowired
    private OaWorkTaskLogDao oaWorkTaskLogDao;

    private FilterRuleBuilder createFilterRuleBuilder(String uid, String title, Integer type, Integer status) {
        FilterRuleBuilder filterRuleBuilder = FilterRuleBuilder.newBuilder();

        if (StringUtils.isNotBlank(title)) {
            filterRuleBuilder.key("title").like().value(title);
        }
        if (StringUtils.isNotBlank(uid)) {
            filterRuleBuilder.and().key("owner_uid").eq().value(uid);
        }
        if (type != null && (type.intValue() == 1 || type.intValue() == 2)) {
            filterRuleBuilder.and().key("type").eq().value(type.intValue());
        }
        if (status != null) {
            switch (status.intValue()) {
                case 0:
                case 1:
                case 2:
                    filterRuleBuilder.and().key("status").eq().value(status.intValue());
                    break;
            }
        }
        return filterRuleBuilder;
    }

    public List<OaWorkTask> findUserTask(String uid, int page, String title, Integer type, Integer status) {
        PageQuery pageQuery = new PageQuery(page, "update_time", "desc");
        pageQuery.setPageNo(page);

        List<FilterRule> rules = createFilterRuleBuilder(uid,  title, type, status).build();
        return findByPage(rules, pageQuery);
    }

    public int countUserTask(String uid, String title, Integer type, Integer status) {
        List<FilterRule> rules = createFilterRuleBuilder(uid,  title, type, status).build();
        return dao.getTotalCount(rules);
    }


    public List<OaWorkTaskLog> getWorkTaskLog(String OaWorkTaskId) {
        PageQuery pageQuery = new PageQuery("op_time", "desc", 0);
        List<FilterRule> rules = FilterRuleBuilder.newBuilder().key("oa_task_id").eq().value(OaWorkTaskId).build();
        return oaWorkTaskLogDao.findByPage(rules, pageQuery);
    }

    public List<OaWorkTask> findByPageSortByTimeByUid(String uid, int pageNo) {
        PageQuery pageQuery = new PageQuery(pageNo, "update_time", "desc");
        pageQuery.setPageNo(pageNo);
        List<FilterRule> rules = FilterRuleBuilder.newBuilder().key("owner_uid").eq().value(uid).build();
        return findByPage(rules, pageQuery);
    }

    public int saveOaWorkTaskLog(OaWorkTaskLog oaWorkTaskLog) {
        return oaWorkTaskLogDao.insertSelective(oaWorkTaskLog);
    }

    public int getMyWorkTaskTotalCount(String uid) {
        List<FilterRule> rules = FilterRuleBuilder.newBuilder().key("owner_uid").eq().value(uid).build();
        return super.getTotalCount(rules);
    }

    public int getWorkTaskTotalCount() {
        return super.getTotalCount(null);
    }
}
