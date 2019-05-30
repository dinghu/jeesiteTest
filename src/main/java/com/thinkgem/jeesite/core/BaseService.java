package com.thinkgem.jeesite.core;

import com.thinkgem.jeesite.common.mysql.FilterRule;
import com.thinkgem.jeesite.common.mysql.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseService<D extends BaseDao<T, KT>, T, KT> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    public int deleteByPrimaryKey(KT id) {
        return dao.deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return dao.insert(record);
    }

    public int insertSelective(T record) {
        return dao.insertSelective(record);
    }

    public T selectByPrimaryKey(KT id) {
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

    public List<T> findByPageSortByTime() {
        PageQuery pageQuery = new PageQuery("update_time", "desc", 0);
        return findByPage(null, pageQuery);
    }

    public List<T> getAll() {
        return dao.findByPage(null, null);
    }
}
