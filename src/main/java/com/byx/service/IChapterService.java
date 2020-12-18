package com.byx.service;

import com.byx.domain.Chapter;

import java.util.List;

/**
 * 电子书章节服务接口
 */
public interface IChapterService
{
    /**
     * 获取章节数据及其对应的电子书信息
     * @param bookId 电子书id
     * @param chapter 章节
     * @return 章节数据和电子书数据
     */
    List<Object> getChapterAndBook(int bookId, int chapter);

    /**
     * 获取电子书目录
     * @param bookId 电子书id
     * @return 目录
     */
    List<Chapter> getContents(int bookId);
}
