/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaFeedback;
import com.thinkgem.jeesite.modules.oa.service.OaFeedbackService;

/**
 * 用户售后反馈Controller
 *
 * @author hale
 * @version 2019-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaFeedback")
public class OaFeedbackController extends BaseController {

    @Autowired
    private OaFeedbackService oaFeedbackService;

    @ModelAttribute
    public OaFeedback get(@RequestParam(required = false) String id) {
        OaFeedback entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = oaFeedbackService.get(id);
        }
        if (entity == null) {
            entity = new OaFeedback();
        }
        return entity;
    }

    @RequiresPermissions("oa:oaFeedback:view")
    @RequestMapping(value = {"list", ""})
    public String list(OaFeedback oaFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OaFeedback> page = oaFeedbackService.findPage(new Page<OaFeedback>(request, response), oaFeedback);
        model.addAttribute("page", page);
        return "modules/oa/oaFeedbackList";
    }

    @RequiresPermissions("oa:oaFeedback:view")
    @RequestMapping(value = "form")
    public String form(OaFeedback oaFeedback, Model model) {
        model.addAttribute("oaFeedback", oaFeedback);
        return "modules/oa/oaFeedbackForm";
    }

    @RequiresPermissions("oa:oaFeedback:edit")
    @RequestMapping(value = "save")
    public String save(OaFeedback oaFeedback, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, oaFeedback)) {
            return form(oaFeedback, model);
        }
        oaFeedbackService.save(oaFeedback);
        addMessage(redirectAttributes, "保存用户售后反馈成功");
        return "redirect:" + Global.getAdminPath() + "/oa/oaFeedback/?repage";
    }

    @RequiresPermissions("oa:oaFeedback:edit")
    @RequestMapping(value = "delete")
    public String delete(OaFeedback oaFeedback, RedirectAttributes redirectAttributes) {
        oaFeedbackService.delete(oaFeedback);
        addMessage(redirectAttributes, "删除用户售后反馈成功");
        return "redirect:" + Global.getAdminPath() + "/oa/oaFeedback/?repage";
    }

    @RequiresPermissions("oa:oaFeedback:view")
    @RequestMapping(value = "detail")
    public String detail(OaFeedback oaFeedback, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("oaFeedback", oaFeedback);
        return "modules/oa/oaFeedbackDetail";
    }
}