package com.byx.service;

import com.byx.domain.Favorite;
import com.byx.domain.PageBean;

import java.util.List;

/**
 * 收藏服务接口
 */
public interface IFavoriteService
{
    /**
     * 分页查询收藏和电子书
     * @param userId 用户id
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 返回指定id用户的收藏列表，列表项格式为[Favorite, Book]
     */
    PageBean<List<Object>> queryFavoriteAndBookByPage(int userId, int pageSize, int currentPage);

    /**
     * 判断指定用户是否收藏指定电子书
     * @param bookId 电子书id
     * @param userId 用户id
     * @return true/false
     */
    boolean isFavorite(int bookId, int userId);

    /**
     * 添加收藏
     * @param favorite 收藏记录
     */
    void add(Favorite favorite);

    /**
     * 取消收藏
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void cancel(int bookId, int userId);
}
