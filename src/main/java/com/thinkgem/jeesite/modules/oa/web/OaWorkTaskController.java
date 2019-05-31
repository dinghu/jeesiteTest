package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.UuidUtls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import com.thinkgem.jeesite.modules.oa.service.OaWorkTaskService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
    public OaWorkTask get(String id) {
        OaWorkTask entity = workTaskService.selectByPrimaryKey(id);
        if (entity == null) {
            entity = new OaWorkTask();
        }
        return entity;
    }

    @RequiresPermissions("oa:oaWorkTask:view")
    @RequestMapping(value = {"list"})
    public String list(OaWorkTask oaWorkTask, HttpServletRequest request, HttpServletResponse response, Model model) {
        oaWorkTask.setSelf(false);
        Page<OaWorkTask> page = new Page<OaWorkTask>(request, response);
        oaWorkTask.setPage(page);
        page.setCount(workTaskService.getWorkTaskTotalCount());
        page.setList(workTaskService.findByPageSortByTime(page.getPageNo()));
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
        String userId = UserUtils.getUserId();
        page.setCount(workTaskService.getMyWorkTaskTotalCount(userId));
        page.setList(workTaskService.findByPageSortByTimeByUid(userId, page.getPageNo()));
        model.addAttribute("page", page);
        return "modules/oa/oaWorkTaskList";
    }


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

    @RequiresPermissions("oa:oaWorkTask:edit")
    @RequestMapping(value = "save")
    public String save(OaWorkTask oaWorkTask, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, oaWorkTask)) {
            return form(oaWorkTask, model);
        }
        OaWorkTask oaWorkTaskDb = workTaskService.selectByPrimaryKey(oaWorkTask.getId());
        if (oaWorkTaskDb != null) {
            //保存任务
            oaWorkTask.setUpdateTime(new Date());
            workTaskService.updateByPrimaryKey(oaWorkTask);

            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setContent("修改了任务");
            oaWorkTaskLog.setOaTaskId(oaWorkTask.getId());
            oaWorkTaskLog.setOpFromUid(UserUtils.getUserId());
            oaWorkTaskLog.setOpToUid(UserUtils.getUserId());
            oaWorkTaskLog.setOpTime(new Date());
            workTaskService.saveOaWorkTaskLog(oaWorkTaskLog);
            addMessage(redirectAttributes, "修改任务'" + oaWorkTask.getTitle() + "'成功");
        } else {
            //保存任务
            String taskId = UuidUtls.getUUID();
            oaWorkTask.setId(taskId);
            oaWorkTask.setCreateTime(new Date());
            oaWorkTask.setUpdateTime(oaWorkTask.getCreateTime());
            oaWorkTask.setOwnerUid(UserUtils.getUserId());
            oaWorkTask.setCreateUid(UserUtils.getUserId());
            workTaskService.insertSelective(oaWorkTask);
            //生成日志记录
            OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
            oaWorkTaskLog.setContent("创建了任务");
            oaWorkTaskLog.setOpFromUid(UserUtils.getUserId());
            oaWorkTaskLog.setOaTaskId(taskId);
            oaWorkTaskLog.setOpToUid(UserUtils.getUserId());
            oaWorkTaskLog.setOpTime(new Date());
            workTaskService.saveOaWorkTaskLog(oaWorkTaskLog);
            addMessage(redirectAttributes, "保存任务'" + oaWorkTask.getTitle() + "'成功");
        }
        return "redirect:" + adminPath + "/oa/oaWorkTask/self?repage";
    }
}
