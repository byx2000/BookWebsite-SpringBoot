package com.byx.service;

import com.byx.domain.PageBean;

import java.util.List;

/**
 * 书签服务接口
 */
public interface IBookmarkService {
    /**
     * 添加书签
     *
     * @param userId    用户id
     * @param chapterId 章节id
     */
    void add(int userId, int chapterId);

    /**
     * 移除书签
     *
     * @param userId    用户id
     * @param chapterId 章节id
     */
    void remove(int userId, int chapterId);

    /**
     * 判断是否添加了书签
     *
     * @param userId    用户id
     * @param chapterId 章节id
     * @return true或false
     */
    boolean isBookmark(int userId, int chapterId);

    /**
     * 获取指定用户的书签列表
     *
     * @param userId      用户id
     * @param bookName    书名搜索关键词
     * @param chapterName 章节名搜索关键词
     * @param pageSize    每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<List<Object>> queryBookmarksOfUser(int userId, String bookName, String chapterName, boolean isDesc, int pageSize, int currentPage);
}
