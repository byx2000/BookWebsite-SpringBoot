package com.byx.query;

public class FavoriteQuery extends Query
{
    private Integer userId;
    private Integer bookId;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getBookId()
    {
        return bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    @Override
    protected void customizeQuery()
    {
        if (userId != null)
            addWhereCondition("userId == ?", userId);

        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);

        addOrderCondition("time DESC");
    }
}
