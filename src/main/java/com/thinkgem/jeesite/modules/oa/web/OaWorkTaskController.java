package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import com.thinkgem.jeesite.modules.oa.service.OaWorkTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oa/oaWorkTask")
public class OaWorkTaskController {
    @Autowired
    OaWorkTaskService workTaskService;

    @ModelAttribute
    public OaWorkTask get(Integer id) {
        OaWorkTask entity = workTaskService.selectByPrimaryKey(id);
        if (entity == null) {
            entity = new OaWorkTask();
        }
        return entity;
    }

    @RequiresPermissions("oa:oaWorkTask:view")
    @RequestMapping(value = {"list", ""})
    public String list(OaWorkTask workTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OaWorkTask> page = new Page<OaWorkTask>(request, response);
        workTask.setPage(page);
        page.setList(workTaskService.findByPage(null, null));
        page.initialize();
        model.addAttribute("page", page);
        return "modules/oa/oaWorkTaskList";
    }

    /**
     * 我的通知列表
     */
    @RequestMapping(value = "self")
    public String selfList(OaWorkTask oaWorkTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        oaWorkTask.setSelf(true);
        Page<OaWorkTask> page = new Page<OaWorkTask>(request, response);
        oaWorkTask.setPage(page);
        page.setList(workTaskService.findByPage(null, null));
        page.initialize();
        model.addAttribute("page", page);
        return "modules/oa/oaWorkTaskList";
    }

    /**
     * 查看我的通知
     */
    @RequestMapping(value = "view")
    public String view(OaWorkTask oaWorkTask, Model model) {
        List<OaWorkTaskLog> oaWorkTaskLogList = workTaskService.getWorkTaskLog(oaWorkTask.getId());
        oaWorkTask.setOaWorkTaskLogList(oaWorkTaskLogList);
        model.addAttribute("oaWorkTask", oaWorkTask);
        return "modules/oa/oaWorkTaskDetail";
    }
}
