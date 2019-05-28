package com.thinkgem.jeesite.core;

import com.thinkgem.jeesite.common.mysql.FilterRule;
import com.thinkgem.jeesite.common.mysql.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseService<D extends BaseDao<T>, T> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return dao.insert(record);
    }

    public int insertSelective(T record) {
        return dao.insertSelective(record);
    }

    public T selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return dao.insertSelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return dao.updateByPrimaryKey(record);
    }

    public List<T> findByPage(@Param("filterRules") List<FilterRule> filterRules, @Param("pageQuery") PageQuery pageQuery) {
        return dao.findByPage(filterRules, pageQuery);
    }

    public List<T> getAll() {
        return dao.findByPage(null, null);
    }
}
