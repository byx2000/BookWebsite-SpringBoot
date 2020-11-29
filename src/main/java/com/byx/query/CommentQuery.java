package com.byx.query;

/**
 * 评论查询类
 */
public class CommentQuery extends Query
{
    private Integer bookId = null;
    private Integer userId = null;
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
        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);

        if (userId != null)
            addWhereCondition("userId == ?", userId);

        addOrderCondition("time DESC");

        if (limit != null)
        {
            addLimit(limit);
            if (offset != null)
                addOffset(offset);
        }
    }
}
