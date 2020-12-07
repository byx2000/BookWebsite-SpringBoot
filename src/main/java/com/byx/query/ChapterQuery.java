package com.byx.query;

/**
 * 电子书章节查询类
 */
public class ChapterQuery extends Query
{
    private Integer bookId;
    private Integer chapter;

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

    @Override
    protected void customizeQuery()
    {
        if (bookId != null)
            addWhereCondition("bookId == ?", bookId);
        if (chapter != null)
            addWhereCondition("chapter == ?", chapter);
    }
}
