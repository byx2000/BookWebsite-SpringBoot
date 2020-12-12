package com.byx.service;

import com.byx.domain.PageBean;

import java.util.List;

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

    /**
     * 获取指定用户的书签列表及其对应的电子书信息
     * @param userId 用户id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<List<Object>> queryBookmarksAndBooksByUserId(int userId, int pageSize, int currentPage);
}
