package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.core.IntegerKeyBaseService;
import com.thinkgem.jeesite.modules.oa.dao.OaWorkTaskLogDao;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import org.springframework.stereotype.Service;

@Service
public class OaWorkLogService extends IntegerKeyBaseService<OaWorkTaskLogDao, OaWorkTaskLog> {
}
