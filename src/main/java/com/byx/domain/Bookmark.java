package com.byx.domain;

/**
 * 书签实体类
 */
public class Bookmark
{
    private Integer id;
    private Integer userId;
    private Integer chapterId;
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

    public Integer getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(Integer chapterId)
    {
        this.chapterId = chapterId;
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
                ", chapterId=" + chapterId +
                ", time='" + time + '\'' +
                '}';
    }
}
