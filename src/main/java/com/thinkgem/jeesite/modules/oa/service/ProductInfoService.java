package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.core.IntegerKeyBaseService;
import com.thinkgem.jeesite.modules.oa.dao.ProductInfoDao;
import com.thinkgem.jeesite.modules.oa.entity.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoService extends IntegerKeyBaseService<ProductInfoDao, ProductInfo> {

    public List<ProductInfo> findByPageAndKeywords(int page, String keywords) {
        PageQuery pageQuery = new PageQuery(page, "update_time", "desc");
        pageQuery.setPageNo(page);
        return dao.findByPageAndKeywords(keywords, pageQuery);
    }

    public int countProductInfoNum(String keywords) {
        return dao.getTotalCountByKeywords(keywords);
    }
}
