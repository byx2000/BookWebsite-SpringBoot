package com.byx.service;

import com.byx.domain.PageBean;

import java.util.List;

/**
 * 收藏服务接口
 */
public interface IFavoriteService
{
    /**
     * 查询当前用户的收藏列表
     * @param userId 用户id
     * @param bookName 书名搜索关键词
     * @param author 作者搜索关键词
     * @param isDesc 是否按照时间降序排序
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据
     */
    PageBean<List<Object>> queryFavoritesOfUser(int userId, String bookName, String author, boolean isDesc, int pageSize, int currentPage);

    /**
     * 判断当前用户是否收藏指定电子书
     * @param bookId 电子书id
     * @param userId 用户id
     * @return true或false
     */
    boolean isFavorite(int bookId, int userId);

    /**
     * 添加收藏
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void add(int bookId, int userId);

    /**
     * 取消收藏
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void cancel(int bookId, int userId);
}
