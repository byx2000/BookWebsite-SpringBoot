package com.byx.controller;

import com.byx.domain.Favorite;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.domain.User;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收藏控制器
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController extends BaseController
{
    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 分页查询当前用户收藏记录
     * @param pageSize 每页显示条数
     * @param currentPage 当前页码
     * @return 分页数据，包含收藏数据和相应的电子书数据
     */
    @RequestMapping("/query")
    public ResultInfo query(Integer pageSize, Integer currentPage)
    {
        // 参数校验
        if (pageSize == null || currentPage == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 获取收藏列表
        PageBean<List<Object>> pageBean = favoriteService.queryFavoriteAndBookByPage(user.getId(), pageSize, currentPage);
        return ResultInfo.success(pageBean);
    }

    /**
     * 判断当前用户是否收藏了指定电子书
     * @param bookId 电子书
     * @return true/false
     */
    @RequestMapping("/isFavorite")
    public ResultInfo isFavorite(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(favoriteService.isFavorite(bookId, user.getId()));
    }

    /**
     * 添加收藏
     * @param favorite 收藏记录
     * @return 操作结果
     */
    @RequestMapping("/add")
    public ResultInfo add(Favorite favorite)
    {
        // 参数校验
        if (favorite.getBookId() == null) return ResultInfo.fail("参数错误");

        // 获取当前登录用户信息
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 只允许操作当前用户的收藏
        favorite.setUserId(user.getId());

        // 保存
        favoriteService.add(favorite);
        return ResultInfo.success();
    }

    /**
     * 取消收藏
     * @param bookId 电子书id
     * @return 操作结果
     */
    @RequestMapping("/cancel")
    public ResultInfo cancel(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        // 登录校验
        User user = getCurrentUser();
        if (user == null) return ResultInfo.fail("当前未登录");

        // 删除当前用户指定的收藏
        favoriteService.cancel(bookId, user.getId());

        return ResultInfo.success();
    }
}
