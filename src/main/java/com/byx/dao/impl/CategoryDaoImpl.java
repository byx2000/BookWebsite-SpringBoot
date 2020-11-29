package com.byx.dao.impl;

import com.byx.dao.ICategoryDao;
import com.byx.domain.Category;
import com.byx.query.CategoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电子书类型数据访问实现类
 */
@Repository
public class CategoryDaoImpl implements ICategoryDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> query(CategoryQuery query)
    {
        return jdbcTemplate.query("SELECT * FROM categories " + query.getQueryString(),
                new BeanPropertyRowMapper<>(Category.class),
                query.getParameters().toArray());
    }
}
