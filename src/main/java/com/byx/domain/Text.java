package com.byx.domain;

/**
 * 电子书文本实体类
 */
public class Text
{
    private Integer id;
    private Integer bookId;
    private Integer chapter;
    private String content;

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

    public Integer getChapter()
    {
        return chapter;
    }

    public void setChapter(Integer chapter)
    {
        this.chapter = chapter;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Text{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", chapter=" + chapter +
                ", content='" + content + '\'' +
                '}';
    }
}
