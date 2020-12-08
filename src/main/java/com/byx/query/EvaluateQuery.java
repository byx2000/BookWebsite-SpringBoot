package com.byx.query;

/**
 * 点评查询类
 */
public class EvaluateQuery extends Query
{
    private Integer evaluateId;
    private Integer bookId;
    private Integer userId;

    public EvaluateQuery(Integer evaluateId, Integer bookId, Integer userId)
    {
        this.evaluateId = evaluateId;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Integer getEvaluateId()
    {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId)
    {
        this.evaluateId = evaluateId;
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

    @Override
    protected void customizeQuery()
    {
        if (evaluateId != null)
            addWhereCondition("id == ?", evaluateId);
        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);
        if (userId != null)
            addWhereCondition("userId == ?", userId);
    }
}
