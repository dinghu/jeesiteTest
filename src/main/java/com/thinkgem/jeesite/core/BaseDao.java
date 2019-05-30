package com.thinkgem.jeesite.core;

import com.thinkgem.jeesite.common.mysql.FilterRule;
import com.thinkgem.jeesite.common.mysql.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T,KT> {
    int deleteByPrimaryKey(KT id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(KT id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> findByPage(@Param("filterRules") List<FilterRule> filterRules, @Param("pageQuery") PageQuery pageQuery);

    int getTotalCount(@Param("filterRules") List<FilterRule> filterRules);
}
