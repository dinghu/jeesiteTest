package com.thinkgem.jeesite.modules.oa.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UuidUtls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.core.Result;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import com.thinkgem.jeesite.modules.oa.service.OaWorkTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.activiti.editor.language.json.converter.util.JsonConverterUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        //查询条件
        String userId = UserUtils.getUserId();
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        Integer iType = null;
        Integer iStatus = null;
        try {
            if (StringUtils.isNumeric(type)) {
                iType = Integer.valueOf(type);
            }
            if (StringUtils.isNumeric(status)) {
                iStatus = Integer.valueOf(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        page.setCount(workTaskService.countUserTask(userId, title, iType, iStatus));
        page.setList(workTaskService.findUserTask(userId, page.getPageNo(), title, iType, iStatus));
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

    @RequestMapping(value = "assignto")
    public String assignto(OaWorkTask oaWorkTask, Model model) {
        model.addAttribute("oaWorkTask", oaWorkTask);
        return "modules/oa/oaTaskAssignTo";
    }

    @RequestMapping(value = "assigntoSave")
    @ResponseBody
    public String assigntoSave(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        String note = httpServletRequest.getParameter("note");
        String id = httpServletRequest.getParameter("id");
        String ownerUid = httpServletRequest.getParameter("ownerUid");
        //更新任务
        OaWorkTask oaWorkTask = get(id);
        oaWorkTask.setOwnerUid(ownerUid);
        oaWorkTask.setUpdateTime(new Date());
        workTaskService.updateByPrimaryKey(oaWorkTask);


        //生成日志记录
        OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
        User user = UserUtils.get(ownerUid);
        String content = "指派任务给 " + user.getName();
        if (StringUtils.isNotBlank(note)) {
            oaWorkTaskLog.setMark(note);
        }
        oaWorkTaskLog.setContent(content);
        oaWorkTaskLog.setOaTaskId(oaWorkTask.getId());
        oaWorkTaskLog.setOpFromUid(UserUtils.getUserId());
        oaWorkTaskLog.setOpToUid(ownerUid);
        oaWorkTaskLog.setOpTime(new Date());
        workTaskService.saveOaWorkTaskLog(oaWorkTaskLog);

        System.out.println(note);
        System.out.println(id);
        return JsonMapper.getInstance().toJson(new Result(0, "成功"));
    }


    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    private void changeStatus(OaWorkTask oaWorkTask, int status) {
        oaWorkTask.setStatus(status);
        workTaskService.updateByPrimaryKey(oaWorkTask);

        //生成日志记录
        User user = UserUtils.getUser();
        OaWorkTaskLog oaWorkTaskLog = new OaWorkTaskLog();
        String content = "标记任务 已完成";
        switch (status) {
            case 1:
                content = "标记任务 进行中";
                break;
            case 2:
                break;
        }
        oaWorkTaskLog.setContent(content);
        oaWorkTaskLog.setOaTaskId(oaWorkTask.getId());
        oaWorkTaskLog.setOpFromUid(UserUtils.getUserId());
        oaWorkTaskLog.setOpTime(new Date());
        workTaskService.saveOaWorkTaskLog(oaWorkTaskLog);
    }

    @RequestMapping(value = "start")
    public String start(OaWorkTask oaWorkTask, Model model, RedirectAttributes redirectAttributes) {
        changeStatus(oaWorkTask, 1);
        return "redirect:" + adminPath + "/oa/oaWorkTask/self?repage";
    }

    @RequestMapping(value = "finish")
    public String finish(OaWorkTask oaWorkTask, Model model, RedirectAttributes redirectAttributes) {
        changeStatus(oaWorkTask, 2);
        addMessage(redirectAttributes, "任务已完成");
        return "redirect:" + adminPath + "/oa/oaWorkTask/self?repage";
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
