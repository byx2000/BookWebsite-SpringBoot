package com.byx.query;

import com.byx.util.DateUtils;

/**
 * 电子书查询类
 */
public class BookQuery extends Query
{
    private Integer bookId = null;
    private Integer categoryId = null;
    private Integer daysAgo = null;
    private String keyword = null;
    private String orderBy = null;
    private Integer limit = null;
    private Integer offset = null;

    public Integer getBookId()
    {
        return bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public Integer getDaysAgo()
    {
        return daysAgo;
    }

    public void setDaysAgo(Integer daysAgo)
    {
        this.daysAgo = daysAgo;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

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

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    @Override
    protected void customizeQuery()
    {
        // where 条件
        if (bookId != null)
            addWhereCondition("id == ?", bookId);

        if (categoryId != null)
            addWhereCondition("categoryId == ?", categoryId);

        if (daysAgo != null && daysAgo > 0)
            addWhereCondition("updateTime >= ?", DateUtils.daysAgo(daysAgo));

        if (keyword != null)
            addWhereCondition("name LIKE ? OR author LIKE ? OR description LIKE ?",
                    "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");

        // order by 条件
        if (orderBy != null)
        {
            switch (orderBy)
            {
                case "updateTime": // 按更新时间排序
                    addOrderCondition("updateTime DESC");
                    break;
                case "wordCount": // 按字数排序
                    addOrderCondition("wordCount DESC");
                    break;
                case "heat": // 按热度排序
                    addOrderCondition("heat DESC");
                    break;
                case "score": // 按得分排序
                    addOrderCondition("score DESC");
                    break;
                case "random": // 随机顺序
                    addOrderCondition("RANDOM()");
                    break;
            }
        }

        if (limit != null)
        {
            addLimit(limit);
            if (offset != null)
                addOffset(offset);
        }
    }
}
