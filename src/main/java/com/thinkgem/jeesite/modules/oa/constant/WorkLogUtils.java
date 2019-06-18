package com.thinkgem.jeesite.modules.oa.constant;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.oa.entity.*;
import com.thinkgem.jeesite.modules.oa.service.OaWorkLogService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class WorkLogUtils {
    private static OaWorkLogService oaWorkLogService = SpringContextHolder.getBean(OaWorkLogService.class);

    public static void saveLoginLog() {
        User user = UserUtils.getUser();
        if (user != null) {
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setType(WorkType.LOGIN);
            oaWorkTaskLog.setOpTime(new Date());
            oaWorkTaskLog.setOpFromUid(user.getId());
            oaWorkTaskLog.setContent(user.getName() + "登录了系统");
            oaWorkLogService.insertSelective(oaWorkTaskLog);
        }
    }


    public static void saveLog(OaWorkTask oaWorkTask, boolean isNew) {
        User user = UserUtils.getUser();
        if (user != null) {
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setType(WorkType.WORKTASK);
            oaWorkTaskLog.setContent(isNew ? "创建任务" : "修改任务");
            oaWorkTaskLog.setOaTaskId(oaWorkTask.getId());
            oaWorkTaskLog.setOpFromUid(user.getId());
            oaWorkTaskLog.setOpToUid(user.getId());
            oaWorkTaskLog.setOpTime(new Date());
            oaWorkTaskLog.setName(oaWorkTaskLog.getContent());
            oaWorkLogService.insertSelective(oaWorkTaskLog);
        }
    }

    public static void saveLog(Product product, boolean isNew) {
        User user = UserUtils.getUser();
        if (user != null) {
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setType(WorkType.PRODUCTINFO);
            oaWorkTaskLog.setOaTaskId(product.getId() + "");
            oaWorkTaskLog.setContent(isNew ? "添加产品" : "修改产品");
            oaWorkTaskLog.setOpFromUid(user.getId());
            oaWorkTaskLog.setOpTime(new Date());
            oaWorkTaskLog.setName(product.getName());
            oaWorkLogService.insertSelective(oaWorkTaskLog);
        }
    }

    public static void saveLog(ProductInfo productInfo, boolean isNew) {
        User user = UserUtils.getUser();
        if (user != null) {
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setType(WorkType.PRODUCTINFO);
            oaWorkTaskLog.setOaTaskId(productInfo.getId() + "");
            oaWorkTaskLog.setContent(isNew ? "添加价格信息" : "修改价格信息");
            oaWorkTaskLog.setOpFromUid(user.getId());
            oaWorkTaskLog.setOpTime(new Date());
            oaWorkTaskLog.setName(productInfo.getName());
            oaWorkLogService.insertSelective(oaWorkTaskLog);
        }
    }

    public static void saveLog(OaFeedback oaFeedback, boolean isNew) {
        User user = UserUtils.getUser();
        if (user != null) {
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setType(WorkType.FEEDBACK);
            oaWorkTaskLog.setOaTaskId(oaFeedback.getId());
            oaWorkTaskLog.setContent(isNew ? "提交售后反馈:" + oaFeedback.getContent() : "修改售后反馈:" + oaFeedback.getContent());
            oaWorkTaskLog.setOpFromUid(user.getId());
            oaWorkTaskLog.setOpTime(new Date());
            oaWorkTaskLog.setName(oaFeedback.getContent());
            oaWorkLogService.insertSelective(oaWorkTaskLog);
        }
    }

}
