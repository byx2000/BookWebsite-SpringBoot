package com.byx.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查询条件封装类基类
 * <p>该类负责封装各种查询条件、生成sql字符串和对应参数</p>
 */
public class Query
{
    private final List<String> whereConditions = new ArrayList<>();
    private final List<String> orderConditions = new ArrayList<>();
    private Integer limit = null;
    private Integer offset = null;
    private final List<Object> parameters = new ArrayList<>();

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public Integer getOffset()
    {
        return offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

    protected void addWhereCondition(String cond, Object... params)
    {
        whereConditions.add(cond);
        parameters.addAll(Arrays.asList(params));
    }

    protected void addOrderCondition(String cond, Object... params)
    {
        orderConditions.add(cond);
        parameters.addAll(Arrays.asList(params));
    }

    protected void addLimit(int limit)
    {
        this.limit = limit;
        parameters.add(limit);
    }

    protected void addOffset(int offset)
    {
        this.offset = offset;
        parameters.add(offset);
    }

    /**
     * 子类实现：自定义查询条件
     */
    protected void customizeQuery() {}

    private void refresh()
    {
        whereConditions.clear();
        orderConditions.clear();
        parameters.clear();
        customizeQuery();
    }

    /**
     * 获取sql查询子句
     * @return sql查询子句，格式为 WHERE ... ORDER BY ... LIMIT ? OFFSET ?
     */
    public String getQueryString()
    {
        refresh();
        StringBuilder sql = new StringBuilder();

        // 拼接where字句
        if (!whereConditions.isEmpty())
        {
            for (int i = 0; i < whereConditions.size(); ++i)
            {
                if (i == 0) sql.append(" WHERE ");
                else sql.append(" AND ");
                sql.append(" (").append(whereConditions.get(i)).append(") ");
            }
        }

        // 拼接order子句
        if (!orderConditions.isEmpty())
        {
            for (int i = 0; i < orderConditions.size(); ++i)
            {
                if (i == 0) sql.append(" ORDER BY ");
                else sql.append(" , ");
                sql.append(orderConditions.get(i));
            }
        }

        // 拼接limit子句
        if (limit != null)
        {
            sql.append(" LIMIT (?) ");
            parameters.add(limit);
            if (offset != null)
            {
                sql.append(" OFFSET (?) ");
                parameters.add(offset);
            }
        }

        return sql.toString();
    }

    /**
     * 获取查询参数
     * @return 查询参数
     */
    public List<Object> getParameters()
    {
        refresh();
        if (limit != null)
        {
            parameters.add(limit);
            if (offset != null)
            {
                parameters.add(offset);
            }
        }
        return parameters;
    }
}
