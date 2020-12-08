package com.byx.dao.impl;

import com.byx.domain.PageBean;
import com.byx.query.IQuery;
import com.byx.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * 所有dao的基类，包含一些公共方法
 */
public class BaseDao
{
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 查询总数
     * @param tableName 表名
     * @param query 查询条件
     * @return 结果总数
     */
    protected int count(String tableName, IQuery query)
    {
        Integer cnt = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableName + " " + query.getQueryString(),
                Integer.class,
                query.getParameters().toArray());
        return cnt == null ? 0 : cnt;
    }

    /**
     * 查询
     * @param <T> 实体类
     * @param tableName 表名
     * @param query 查询条件
     * @param entityClass 实体类
     * @return 结果列表
     */
    protected <T> List<T> query(String tableName, IQuery query, Class<T> entityClass)
    {
        return jdbcTemplate.query("SELECT * FROM " + tableName + " " + query.getQueryString(),
                new BeanPropertyRowMapper<>(entityClass),
                query.getParameters().toArray());
    }

    /**
     * 分页查询
     * @param <T> 实体类
     * @param tableName 表名
     * @param query 查询条件
     * @param entityClass 实体类
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    protected <T> PageBean<T> queryByPage(String tableName, Query query, Class<T> entityClass, int pageSize, int currentPage)
    {
        // 计算结果总数
        query.setLimit(null);
        query.setOffset(null);
        int totalCount = count(tableName, query);

        // 获取查询结果
        query.setLimit(pageSize);
        query.setOffset(pageSize * (currentPage - 1));
        List<T> list = query(tableName, query, entityClass);

        // 组装PageBean
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(totalCount);
        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(list);

        return pageBean;
    }

    /**
     * 保存
     * @param tableName 表名
     * @param object 要保存的实体类
     * @return 新插入记录的id
     */
    protected int save(String tableName, Object object)
    {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        return (int) simpleJdbcInsert.withTableName(tableName)
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new BeanPropertySqlParameterSource(object));
    }

    /**
     * 删除
     * @param tableName 表名
     * @param query 查询条件
     */
    protected void delete(String tableName, IQuery query)
    {
        jdbcTemplate.update("DELETE FROM " + tableName + " " + query.getQueryString(),
                query.getParameters().toArray());
    }
}
