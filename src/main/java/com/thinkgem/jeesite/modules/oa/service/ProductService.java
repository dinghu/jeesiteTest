package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.core.IntegerKeyBaseService;
import com.thinkgem.jeesite.modules.oa.dao.ProductDao;
import com.thinkgem.jeesite.modules.oa.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends IntegerKeyBaseService<ProductDao, Product> {
    public List<Product> findByPageAndKeywords(int page, String keywords) {
        PageQuery pageQuery = new PageQuery(page, "update_time", "desc");
        pageQuery.setPageNo(page);
        return dao.findByPageAndKeywords(keywords, pageQuery);
    }

    public int countProductNum(String keywords) {
        return dao.getTotalCountByKeywords(keywords);
    }
}
