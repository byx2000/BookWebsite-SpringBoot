package com.byx.query;

/**
 * 评论查询类
 */
public class CommentQuery extends Query
{
    private Integer commentId = null;
    private Integer bookId = null;
    private Integer userId = null;
    private boolean isDelete = false;

    public Integer getCommentId()
    {
        return commentId;
    }

    public void setCommentId(Integer commentId)
    {
        this.commentId = commentId;
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
        if (commentId != null)
            addWhereCondition("id == ?", commentId);

        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);

        if (userId != null)
            addWhereCondition("userId == ?", userId);

        if (!isDelete)
            addOrderCondition("time DESC");
    }
}
