package com.byx.query;

public class FavoriteQuery extends Query
{
    private Integer favoriteId;
    private Integer userId;
    private Integer bookId;
    private boolean isDelete = false;

    public Integer getFavoriteId()
    {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId)
    {
        this.favoriteId = favoriteId;
    }

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
        if (favoriteId != null)
            addWhereCondition("id == ?", favoriteId);

        if (userId != null)
            addWhereCondition("userId == ?", userId);

        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);

        if (!isDelete)
            addOrderCondition("time DESC");
    }
}
