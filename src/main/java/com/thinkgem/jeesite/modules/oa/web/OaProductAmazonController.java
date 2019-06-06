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
import com.thinkgem.jeesite.modules.oa.entity.OaProductAmazon;
import com.thinkgem.jeesite.modules.oa.service.OaProductAmazonService;

/**
 * 亚马逊销售产品价格表Controller
 * @author hale
 * @version 2019-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProductAmazon")
public class OaProductAmazonController extends BaseController {

	@Autowired
	private OaProductAmazonService oaProductAmazonService;
	
	@ModelAttribute
	public OaProductAmazon get(@RequestParam(required=false) String id) {
		OaProductAmazon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProductAmazonService.get(id);
		}
		if (entity == null){
			entity = new OaProductAmazon();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaProductAmazon:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProductAmazon oaProductAmazon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProductAmazon> page = oaProductAmazonService.findPage(new Page<OaProductAmazon>(request, response), oaProductAmazon); 
		model.addAttribute("page", page);
		return "modules/oa/oaProductAmazonList";
	}

	@RequiresPermissions("oa:oaProductAmazon:view")
	@RequestMapping(value = "form")
	public String form(OaProductAmazon oaProductAmazon, Model model) {
		model.addAttribute("oaProductAmazon", oaProductAmazon);
		return "modules/oa/oaProductAmazonForm";
	}

	@RequiresPermissions("oa:oaProductAmazon:edit")
	@RequestMapping(value = "save")
	public String save(OaProductAmazon oaProductAmazon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProductAmazon)){
			return form(oaProductAmazon, model);
		}
		oaProductAmazonService.save(oaProductAmazon);
		addMessage(redirectAttributes, "保存亚马逊销售产品价格成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductAmazon/?repage";
	}
	
	@RequiresPermissions("oa:oaProductAmazon:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProductAmazon oaProductAmazon, RedirectAttributes redirectAttributes) {
		oaProductAmazonService.delete(oaProductAmazon);
		addMessage(redirectAttributes, "删除亚马逊销售产品价格成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductAmazon/?repage";
	}

}