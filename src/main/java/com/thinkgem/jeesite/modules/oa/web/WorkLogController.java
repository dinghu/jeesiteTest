package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaFeedback;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import com.thinkgem.jeesite.modules.oa.entity.Product;
import com.thinkgem.jeesite.modules.oa.service.OaWorkLogService;
import com.thinkgem.jeesite.modules.oa.service.OaWorkTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/oa/oaWorkLog")
public class WorkLogController extends BaseController {
    @Autowired
    OaWorkLogService logService;

    @ModelAttribute
    public OaWorkTaskLog get(@RequestParam(required = false) Integer id) {
        OaWorkTaskLog entity = null;
        if (id != null && id > 0) {
            entity = logService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new OaWorkTaskLog();
        }
        return entity;
    }

    // type  0 : 工作任务  1 产品价格表  2. 产品信息表  3. 登录系统

    @RequestMapping(value = {"list"})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OaWorkTaskLog> page = new Page<OaWorkTaskLog>(request, response);
        page.setCount(logService.getTotalCount(null));
        PageQuery pageQuery = new PageQuery(page.getPageNo(), "op_time", "desc");
        page.setList(logService.findByPage(null, pageQuery));
        model.addAttribute("page", page);
        return "modules/oa/oaWorkLogList";
    }
}
