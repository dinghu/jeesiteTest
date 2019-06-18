package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Product;
import com.thinkgem.jeesite.modules.oa.service.ProductService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

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

    /**
     * 导出数据
     *
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "产品信息表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            new ExportExcel("产品信息表", Product.class).setDataList(productService.getAll()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出产品信息表失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/oa/product/list?repage";
    }


    /**
     * 导入数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/oa/product/list?repage";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Product> list = ei.getDataList(Product.class);
            for (Product product : list) {
                try {
                    Product product2 = productService.selectBySku(product.getSku());
                    if (product2 == null) {
                        productService.insertSelective(product);
                        successNum++;
                    } else {
                        product.setId(product2.getId());
                        productService.updateByPrimaryKey(product2);
                        successNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>sku " + product.getSku() + "," + product.getName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>sku " + product.getSku() + "," + product.getName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条记录，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条记录" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/oa/product/list?repage";
    }
}
