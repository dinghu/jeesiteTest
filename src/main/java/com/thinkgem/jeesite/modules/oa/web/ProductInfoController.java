package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTask;
import com.thinkgem.jeesite.modules.oa.entity.OaWorkTaskLog;
import com.thinkgem.jeesite.modules.oa.entity.ProductInfo;
import com.thinkgem.jeesite.modules.oa.service.ProductInfoService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/oa/productInfo")
public class ProductInfoController extends BaseController {
    @Autowired
    ProductInfoService productInfoService;

    @ModelAttribute
    public ProductInfo get(@RequestParam(required = false) Integer id) {
        ProductInfo entity = null;
        if (id != null) {
            entity = productInfoService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new ProductInfo();
        }
        return entity;
    }

    @RequestMapping(value = {"list"})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ProductInfo> page = new Page<ProductInfo>(request, response);
        String keywords = request.getParameter("keywords");
        page.setCount(productInfoService.countProductInfoNum(keywords));
        page.setList(productInfoService.findByPageAndKeywords(page.getPageNo(), keywords));
        model.addAttribute("page", page);
        System.out.println("keywords:" + keywords);
        return "modules/oa/oaProductInfoList";
    }

    @RequestMapping(value = "form")
    public String form(ProductInfo productInfo, Model model) {
        model.addAttribute("productInfo", productInfo);
        return "modules/oa/oaProductInfoForm";
    }

    @RequestMapping(value = "delete")
    public String delete(ProductInfo productInfo, RedirectAttributes redirectAttributes) {
        productInfoService.deleteByPrimaryKey(productInfo.getId());
        addMessage(redirectAttributes, "删除商品信息成功");
        return "redirect:" + adminPath + "/oa/productInfo/list?repage";
    }

    @RequestMapping(value = "save")
    public String save(ProductInfo productInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, productInfo)) {
            return form(productInfo, model);
        }
        ProductInfo productInfoDb = productInfoService.selectByPrimaryKey(productInfo.getId());
        if (productInfoDb != null) {
            productInfoDb.setUpdateTime(new Date());
            productInfoService.updateByPrimaryKeySelective(productInfo);
            addMessage(redirectAttributes, "商品信息已更新！");
        } else {
            productInfo.setUpdateTime(new Date());
            productInfo.setCreateTime(productInfo.getUpdateTime());
            productInfo.setCreateUid(UserUtils.getUserId());
            productInfoService.insertSelective(productInfo);
            addMessage(redirectAttributes, "商品信息已保存！");
        }

        return "redirect:" + adminPath + "/oa/productInfo/list?repage";
    }
}
