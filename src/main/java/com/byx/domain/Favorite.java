package com.byx.domain;

/**
 * 收藏记录实体类
 */
public class Favorite
{
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private String time;

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

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "Favorite{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", time='" + time + '\'' +
                '}';
    }
}
