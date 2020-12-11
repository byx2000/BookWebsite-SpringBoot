package com.byx.domain;

/**
 * 书签实体类
 */
public class Bookmark
{
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer chapter;
    private String time;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    public Integer getChapter()
    {
        return chapter;
    }

    public void setChapter(Integer chapter)
    {
        this.chapter = chapter;
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
        return "Bookmark{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", chapter=" + chapter +
                ", time='" + time + '\'' +
                '}';
    }
}
