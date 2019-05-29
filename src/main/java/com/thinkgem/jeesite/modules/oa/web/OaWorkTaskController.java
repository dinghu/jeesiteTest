package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oa/oaWorkTask")
public class OaWorkTaskController extends BaseController {
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
        page.setList(workTaskService.findByPageSortByTime());
        page.initialize();
        model.addAttribute("page", page);
        return "modules/oa/oaWorkTaskList";
    }

    /**
     * 我的通知列表
     */
    @RequestMapping(value = "self")
    @RequiresPermissions({"oa:oaWorkTask:edit"})
    public String selfList(OaWorkTask oaWorkTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        oaWorkTask.setSelf(true);
        Page<OaWorkTask> page = new Page<OaWorkTask>(request, response);
        oaWorkTask.setPage(page);
        page.setList(workTaskService.findByPageSortByTime());
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

    @RequestMapping(value = "form")
    public String form(OaWorkTask oaWorkTask, Model model) {
        List<OaWorkTaskLog> oaWorkTaskLogList = workTaskService.getWorkTaskLog(oaWorkTask.getId());
        oaWorkTask.setOaWorkTaskLogList(oaWorkTaskLogList);
        model.addAttribute("oaWorkTask", oaWorkTask);
        return "modules/oa/oaWorkTaskForm";
    }


    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    @RequiresPermissions("oa:oaNotify:edit")
    @RequestMapping(value = "save")
    public String save(OaWorkTask oaWorkTask, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, oaWorkTask)) {
            return form(oaWorkTask, model);
        }
        OaWorkTask oaWorkTaskDb = workTaskService.selectByPrimaryKey(oaWorkTask.getId());
        if (oaWorkTaskDb != null) {
            workTaskService.updateByPrimaryKey(oaWorkTask);
            oaWorkTask.setUpdateTime(oaWorkTask.getCreateTime());
            addMessage(redirectAttributes, "添加任务'" + oaWorkTask.getTitle() + "'成功");
        } else {
            //保存任务
            oaWorkTask.setCreateTime(new Date());
            oaWorkTask.setUpdateTime(oaWorkTask.getCreateTime());
            workTaskService.insertSelective(oaWorkTask);
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setContent("创建了任务");
            oaWorkTaskLog.setOaTaskId(oaWorkTask.getId());
            oaWorkTaskLog.setOpFromUid(1);
            oaWorkTaskLog.setOpToUid(1);
            oaWorkTaskLog.setOpTime(new Date());
            workTaskService.saveOaWorkTaskLog(oaWorkTaskLog);
            addMessage(redirectAttributes, "保存任务'" + oaWorkTask.getTitle() + "'成功");
        }
        return "redirect:" + adminPath + "/oa/oaWorkTask/self?repage";
    }
}
