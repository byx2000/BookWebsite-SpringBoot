package com.byx.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查询工具类
 */
public class Query
{
    private String tableName;
    private String columns;
    private final List<String> wheres = new ArrayList<>();
    private final List<Object> whereParams = new ArrayList<>();
    private final List<String> orders = new ArrayList<>();
    private final List<Boolean> orderDescs = new ArrayList<>();
    private Integer limit;
    private Integer offset;

    /**
     * 设置表名
     * @param tableName 表名
     * @return 当前查询对象
     */
    public Query setTableName(String tableName)
    {
        this.tableName = tableName;
        return this;
    }

    /**
     * 设置列
     * @param columns 列名
     * @return 当前查询对象
     */
    public Query setColumn(String columns)
    {
        this.columns = columns;
        return this;
    }

    /**
     * 添加WHERE子句条件
     * @param cond 条件
     * @param params 参数
     * @return 当前查询对象
     */
    public Query addWhere(String cond, Object... params)
    {
        wheres.add(cond);
        whereParams.addAll(Arrays.asList(params));
        return this;
    }

    /**
     * 添加ORDER BY子句条件
     * @param cond 条件
     * @return 当前查询对象
     */
    public Query addOrder(String cond)
    {
        orders.add(cond);
        orderDescs.add(false);
        return this;
    }

    /**
     * 添加ORDER BY子句条件
     * @param cond 条件
     * @param desc 是否降序
     * @return 当前查询对象
     */
    public Query addOrder(String cond, boolean desc)
    {
        orders.add(cond);
        orderDescs.add(desc);
        return this;
    }

    /**
     * 设置limit值
     * @param limit limit
     * @return 当前查询对象
     */
    public Query setLimit(Integer limit)
    {
        this.limit = limit;
        return this;
    }

    /**
     * 设置offset值
     * @param offset offset
     * @return 当前查询对象
     */
    public Query setOffset(Integer offset)
    {
        this.offset = offset;
        return this;
    }

    /**
     * 获取查询sql字符串
     * @return sql字符串
     */
    public String getQueryClause()
    {
        StringBuilder sql = new StringBuilder();

        // 拼接表名和列名
        if (tableName != null && !columns.isEmpty())
        {
            sql.append("SELECT ").append(columns).append(" FROM ").append(tableName).append(" ");
        }

        // 拼接where子句
        if (!wheres.isEmpty()) sql.append(" WHERE ");
        for (int i = 0; i < wheres.size(); ++i)
        {
            if (i > 0) sql.append(" AND ");
            sql.append(" ( ").append(wheres.get(i)).append(" ) ");
        }

        // 拼接order by子句
        if (!orders.isEmpty()) sql.append(" ORDER BY ");
        for (int i = 0; i < orders.size(); ++i)
        {
            sql.append(" ( ").append(orders.get(i)).append(" ) ");
            if (orderDescs.get(i)) sql.append(" DESC ");
            if (i != orders.size() - 1) sql.append(",");
        }

        // 拼接LIMIT 子句
        if (limit != null)
        {
            sql.append(" LIMIT ? ");
            if (offset != null) sql.append(" OFFSET ? ");
        }

        System.out.println("query clause: " + sql.toString());
        return sql.toString();
    }

    /**
     * 获取删除sql字符串
     * @return sql字符串
     */
    public String getDeleteCaluse()
    {
        StringBuilder sql = new StringBuilder();

        // 拼接表名
        if (tableName != null)
            sql.append("DELETE FROM ").append(tableName).append(" ");

        // 拼接where子句
        if (!wheres.isEmpty()) sql.append(" WHERE ");
        for (int i = 0; i < wheres.size(); ++i)
        {
            if (i > 0) sql.append(" AND ");
            sql.append(" ( ").append(wheres.get(i)).append(" ) ");
        }

        System.out.println("delete clause: " + sql.toString());
        return sql.toString();
    }

    /**
     * 获取查询参数
     * @return 参数数组
     */
    public List<Object> getQueryParams()
    {
        List<Object> params = new ArrayList<>(whereParams);
        if (limit != null) params.add(limit);
        if (offset != null) params.add(offset);

        System.out.println("query params: " + params);
        return params;
    }

    /**
     * 获取删除参数
     * @return 参数数组
     */
    public List<Object> getDeleteParams()
    {
        System.out.println("delete params: " + whereParams);
        return whereParams;
    }
}
