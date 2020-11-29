package com.byx.service;

import com.byx.domain.Category;
import com.byx.query.CategoryQuery;

import java.util.List;

/**
 * 电子书类型服务接口
 */
public interface ICategoryService
{
    /**
     * 根据条件查询电子书类型
     * @param query 查询条件
     * @return 类型列表
     */
    List<Category> query(CategoryQuery query);
}
