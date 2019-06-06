package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.mysql.PageQuery;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.core.IntegerBaseDao;
import com.thinkgem.jeesite.modules.oa.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ProductDao extends IntegerBaseDao<Product> {
    List<Product> findByPageAndKeywords(@Param("keywords") String keywords, @Param("pageQuery") PageQuery pageQuery);

    int getTotalCountByKeywords(@Param("keywords") String keywords);
}