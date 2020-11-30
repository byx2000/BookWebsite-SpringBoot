package com.byx.query;

public class FavoriteQuery extends Query
{
    private Integer userId;
    private Integer limit;
    private Integer offset;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
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

    @Override
    protected void customizeQuery()
    {
        if (userId != null)
            addWhereCondition("userId == ?", userId);

        if (limit != null)
        {
            addLimit(limit);
            if (offset != null)
                addOffset(offset);
        }

        addOrderCondition("time DESC");
    }
}
