package com.byx.service.impl;

import com.byx.dao.ICategoryDao;
import com.byx.domain.Category;
import com.byx.query.IQuery;
import com.byx.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电子书类型服务实现类
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public List<Category> query(IQuery query)
    {
        return categoryDao.query(query);
    }
}
