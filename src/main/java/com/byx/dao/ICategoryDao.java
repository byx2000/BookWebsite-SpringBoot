package com.byx.dao;

import com.byx.domain.Category;
import com.byx.query.IQuery;

import java.util.List;

/**
 * 电子书类型数据访问接口
 */
public interface ICategoryDao
{
    /**
     * 根据条件查询电子书类型
     * @param query 查询条件
     * @return 类型列表
     */
    List<Category> query(IQuery query);
}
