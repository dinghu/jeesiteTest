package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.constant.WorkLogUtils;
import com.thinkgem.jeesite.modules.oa.entity.ProductInfo;
import com.thinkgem.jeesite.modules.oa.service.ProductInfoService;
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
            WorkLogUtils.saveLog(productInfo, false);
        } else {
            productInfo.setUpdateTime(new Date());
            productInfo.setCreateTime(productInfo.getUpdateTime());
            productInfo.setCreateUid(UserUtils.getUserId());
            productInfoService.insertSelective(productInfo);
            addMessage(redirectAttributes, "商品信息已保存！");
            WorkLogUtils.saveLog(productInfo, true);
        }

        return "redirect:" + adminPath + "/oa/productInfo/list?repage";
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
            new ExportExcel("产品信息表", ProductInfo.class).setDataList(productInfoService.getAll(

            )).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出产品信息表失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/oa/productInfo/list?repage";
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
            return "redirect:" + adminPath + "/oa/productInfo/list?repage";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<ProductInfo> list = ei.getDataList(ProductInfo.class);
            for (ProductInfo productInfo : list) {
                try {
                    ProductInfo productInfo2 = productInfoService.selectBySku(productInfo.getSku());
                    if (productInfo2 == null) {
                        productInfoService.insertSelective(productInfo);
                        successNum++;
                    } else {
                        productInfo.setId(productInfo2.getId());
                        productInfoService.updateByPrimaryKey(productInfo2);
                        successNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>sku " + productInfo.getSku() + "," + productInfo.getName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>sku " + productInfo.getSku() + "," + productInfo.getName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条记录，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条记录" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/oa/productInfo/list?repage";
    }
}
