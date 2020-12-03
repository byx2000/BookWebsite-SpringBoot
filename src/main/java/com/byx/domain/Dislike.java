package com.byx.domain;

/**
 * 踩记录实体类
 */
public class Dislike
{
    private Integer id;
    private Integer bookId;
    private Integer userId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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
    public String toString()
    {
        return "Dislike{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
