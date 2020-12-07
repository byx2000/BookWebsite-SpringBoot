package com.byx.service;

import com.byx.domain.Text;

import java.util.List;

/**
 * 电子书文本服务接口
 */
public interface ITextService
{
    /**
     * 获取电子书章节数
     * @param bookId 电子书id
     * @return 章节数
     */
    int getChapterCount(int bookId);

    /**
     * 获取章节数据及其对应的电子书信息
     * @param bookId 电子书id
     * @param chapter 章节
     * @return [[Text, Book], ...]
     */
    List<Object> getChapter(int bookId, int chapter);
}
