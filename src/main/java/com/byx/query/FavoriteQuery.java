package com.byx.query;

public class FavoriteQuery extends Query
{
    private Integer userId;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    @Override
    protected void customizeQuery()
    {
        if (userId != null)
            addWhereCondition("userId == ?", userId);

        addOrderCondition("time DESC");
    }
}
