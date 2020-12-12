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
    void add(int userId, int bookId, int chapter);

    /**
     * 移除书签
     * @param userId 用户id
     * @param bookId 电子书id
     * @param chapter 章节
     */
    void remove(int userId, int bookId, int chapter);

    /**
     * 判断是否添加了书签
     * @param userId 用户id
     * @param bookId 电子书id
     * @param chapter 章节
     * @return true/false
     */
    boolean isBookmark(int userId, int bookId, int chapter);
}
