package com.byx.dao.impl;

import com.byx.dao.ICategoryDao;
import com.byx.domain.Category;
import com.byx.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书类型数据访问实现类
 */
@Repository
public class CategoryDaoImpl extends BaseDao implements ICategoryDao
{
    @Override
    public List<Category> query(Query query)
    {
        return query("categories", query, Category.class);
    }
}
