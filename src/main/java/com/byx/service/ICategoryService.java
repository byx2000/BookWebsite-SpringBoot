package com.byx.service;

import com.byx.domain.Category;

import java.util.List;

/**
 * 电子书类型服务接口
 */
public interface ICategoryService
{
    /**
     * 获取所有电子书类型
     * @return 类型列表
     */
    List<Category> queryAll();

    /**
     * 根据id查询电子书类型
     * @param categoryId 类型id
     * @return 指定id的类型
     */
    Category queryById(int categoryId);
}
