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
     * 查询
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

    /**
     * 分页查询
     * @param query 查询条件
     * @param tableName 表名
     * @param entityClass 实体类
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @param <T> 实体类
     * @return 分页数据
     */
    protected <T> PageBean<T> queryByPage(Query query, String tableName, Class<T> entityClass, int pageSize, int currentPage)
    {
        // 计算结果总数
        query.setLimit(null);
        query.setOffset(null);
        int totalCount = count(query, tableName);

        // 获取查询结果
        query.setLimit(pageSize);
        query.setOffset(pageSize * (currentPage - 1));
        List<T> list = query(query, tableName, entityClass);

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
     * @param object 要保存的实体类
     * @param tableName 表名
     */
    protected void save(Object object, String tableName)
    {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(tableName)
                .usingGeneratedKeyColumns("id")
                .execute(new BeanPropertySqlParameterSource(object));
    }

    /**
     * 删除
     * @param query 查询条件
     * @param tableName 表名
     */
    protected void delete(IQuery query, String tableName)
    {
        jdbcTemplate.update("DELETE FROM " + tableName + " " + query.getQueryString(),
                query.getParameters().toArray());
    }

    /**
     * 根据id删除
     * @param id id
     * @param tableName 表名
     */
    protected void deleteById(int id, String tableName)
    {
        jdbcTemplate.update("DELETE FROM " + tableName + " WHERE id == ?", id);
    }
}
