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
     * @param chapterId 章节id
     */
    void add(int userId, int chapterId);

    /**
     * 移除书签
     * @param userId 用户id
     * @param chapterId 章节id
     */
    void remove(int userId, int chapterId);

    /**
     * 判断是否添加了书签
     * @param userId 用户id
     * @param chapterId 章节id
     * @return true/false
     */
    boolean isBookmark(int userId, int chapterId);

    /**
     * 获取指定用户的书签列表及其对应的电子书和章节信息
     * @param userId 用户id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<List<Object>> queryBookmarksAndBooksAndChaptersByUserId(int userId, int pageSize, int currentPage);
}
