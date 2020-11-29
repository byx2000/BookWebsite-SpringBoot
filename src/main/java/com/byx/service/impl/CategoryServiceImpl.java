package com.byx.service.impl;

import com.byx.dao.ICategoryDao;
import com.byx.domain.Category;
import com.byx.query.CategoryQuery;
import com.byx.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电子书类型服务实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> query(CategoryQuery query)
    {
        return categoryDao.query(query);
    }
}
