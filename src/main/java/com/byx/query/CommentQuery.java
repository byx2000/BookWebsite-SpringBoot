package com.byx.query;

/**
 * 评论查询类
 */
public class CommentQuery extends Query
{
    private Integer bookId = null;
    private Integer userId = null;

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

    @Override
    protected void customizeQuery()
    {
        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);

        if (userId != null)
            addWhereCondition("userId == ?", userId);

        addOrderCondition("time DESC");
    }
}
