package com.byx.dao.impl;

import com.byx.domain.PageBean;
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
public abstract class BaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取JdbcTemplate
     *
     * @return JdbcTemplate
     */
    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 获取表名（子类实现）
     *
     * @return 表名
     */
    protected abstract String getTableName();

    /**
     * 查询总数
     *
     * @param query 查询条件
     * @return 结果总数
     */
    protected int count(Query query) {
        query.setTableName(getTableName()).setColumn("COUNT(*)");
        Integer cnt = getJdbcTemplate().queryForObject(query.getQueryClause(),
                Integer.class,
                query.getQueryParams().toArray());
        return cnt == null ? 0 : cnt;
    }

    /**
     * 查询
     *
     * @param <T>         实体类
     * @param query       查询条件
     * @param entityClass 实体类
     * @return 结果列表
     */
    protected <T> List<T> query(Query query, Class<T> entityClass) {
        query.setTableName(getTableName()).setColumn("*");
        return getJdbcTemplate().query(query.getQueryClause(),
                new BeanPropertyRowMapper<>(entityClass),
                query.getQueryParams().toArray());
    }

    /**
     * 分页查询
     *
     * @param <T>         实体类
     * @param query       查询条件
     * @param entityClass 实体类
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    protected <T> PageBean<T> queryByPage(Query query, Class<T> entityClass, int pageSize, int currentPage) {
        // 计算结果总数
        query.setLimit(null);
        query.setOffset(null);
        int totalCount = count(query);

        // 获取查询结果
        query.setLimit(pageSize);
        query.setOffset(pageSize * (currentPage - 1));
        List<T> list = query(query, entityClass);

        // 组装PageBean
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(totalCount);
        pageBean.setData(list);

        return pageBean;
    }

    /**
     * 保存
     *
     * @param object 要保存的实体类
     * @return 新插入记录的id
     */
    protected int save(Object object) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(getJdbcTemplate());
        return (int) simpleJdbcInsert.withTableName(getTableName())
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new BeanPropertySqlParameterSource(object));
    }

    /**
     * 删除
     *
     * @param query 查询条件
     */
    protected void delete(Query query) {
        query.setTableName(getTableName());
        getJdbcTemplate().update(query.getDeleteCaluse(),
                query.getDeleteParams().toArray());
    }
}
