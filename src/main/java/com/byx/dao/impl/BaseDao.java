package com.byx.dao.impl;

import com.byx.query.IQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 所有dao的基类，包含一些公共方法
 */
public class BaseDao
{
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 根据条件查询结果总数
     * @param query 查询条件
     * @param tableName 表名
     * @return 结果总数
     */
    protected int count(IQuery query, String tableName)
    {
        Integer cnt = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableName + " " + query.getQueryString(),
                Integer.class,
                query.getParameters().toArray());
        return cnt == null ? 0 : cnt;
    }

    /**
     * 根据条件查询结果列表
     * @param query 查询条件
     * @param tableName 表名
     * @param entityClass 实体类
     * @param <T> 实体类
     * @return 结果列表
     */
    protected <T> List<T> query(IQuery query, String tableName, Class<T> entityClass)
    {
        return jdbcTemplate.query("SELECT * FROM " + tableName + " " + query.getQueryString(),
                new BeanPropertyRowMapper<>(entityClass),
                query.getParameters().toArray());
    }
}
