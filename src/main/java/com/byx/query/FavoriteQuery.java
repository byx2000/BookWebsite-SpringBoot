package com.byx.query;

/**
 * 收藏查询类
 */
public class FavoriteQuery extends Query
{
    private Integer bookId;
    private Integer userId;
    private boolean isDelete;

    public FavoriteQuery(Integer bookId, Integer userId, boolean isDelete)
    {
        this.bookId = bookId;
        this.userId = userId;
        this.isDelete = isDelete;
    }

    public Integer getBookId()
    {
        return bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public boolean isDelete()
    {
        return isDelete;
    }

    public void setDelete(boolean delete)
    {
        isDelete = delete;
    }

    @Override
    protected void customizeQuery()
    {
        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);
        if (userId != null)
            addWhereCondition("userId == ?", userId);
        if (!isDelete)
            addOrderCondition("time DESC");
    }
}
