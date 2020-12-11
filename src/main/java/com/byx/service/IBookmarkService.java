package com.byx.service;

/**
 * 书签服务接口
 */
public interface IBookmarkService
{
    /**
     * 添加书签
     * @param userId 用户id
     * @param bookId 电子书id
     * @param chapter 章节
     */
    void addBookmark(int userId, int bookId, int chapter);
}
