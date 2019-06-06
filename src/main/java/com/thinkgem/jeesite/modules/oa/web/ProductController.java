package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Product;
import com.thinkgem.jeesite.modules.oa.service.ProductService;
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

@Controller
@RequestMapping(value = "${adminPath}/oa/product")
public class ProductController extends BaseController {
    @Autowired
    ProductService productService;

    @ModelAttribute
    public Product get(@RequestParam(required = false) Integer id) {
        Product entity = null;
        if (id != null) {
            entity = productService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new Product();
        }
        return entity;
    }

    @RequestMapping(value = {"list"})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Product> page = new Page<Product>(request, response);
        String keywords = request.getParameter("keywords");
        page.setCount(productService.countProductNum(keywords));
        page.setList(productService.findByPageAndKeywords(page.getPageNo(), keywords));
        model.addAttribute("page", page);
        System.out.println("keywords:" + keywords);
        return "modules/oa/oaProductList";
    }

    @RequestMapping(value = "form")
    public String form(Product product, Model model) {
        model.addAttribute("product", product);
        return "modules/oa/oaProductForm";
    }

    @RequestMapping(value = "delete")
    public String delete(Product Product, RedirectAttributes redirectAttributes) {
        productService.deleteByPrimaryKey(Product.getId());
        addMessage(redirectAttributes, "删除商品信息成功");
        return "redirect:" + adminPath + "/oa/product/list?repage";
    }

    @RequestMapping(value = "save")
    public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, product)) {
            return form(product, model);
        }
        Product productDb = productService.selectByPrimaryKey(product.getId());
        if (productDb != null) {
            productDb.setUpdateTime(new Date());
            productService.updateByPrimaryKeySelective(product);
            addMessage(redirectAttributes, "商品信息已更新！");
        } else {
            product.setUpdateTime(new Date());
            product.setCreateTime(product.getUpdateTime());
            product.setCreateUid(UserUtils.getUserId());
            productService.insertSelective(product);
            addMessage(redirectAttributes, "商品信息已保存！");
        }

        return "redirect:" + adminPath + "/oa/product/list?repage";
    }
}
