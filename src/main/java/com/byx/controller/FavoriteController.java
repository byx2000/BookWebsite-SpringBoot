package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 收藏控制器
 */
@RestController
@RequestMapping("/favorite")
@Validated
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
    @RequireLogin
    public ResultInfo query(@NotNull Integer pageSize,
                            @NotNull Integer currentPage)
    {
        PageBean<List<Object>> pageBean = favoriteService.queryFavoritesOfUser(
                getCurrentUser().getId(), pageSize, currentPage);
        return ResultInfo.success(pageBean);
    }

    /**
     * 判断当前用户是否收藏了指定电子书
     * @param bookId 电子书
     * @return true/false
     */
    @RequestMapping("/isFavorite")
    @RequireLogin
    public ResultInfo isFavorite(@NotNull Integer bookId)
    {
        return ResultInfo.success(favoriteService.isFavorite(bookId, getCurrentUser().getId()));
    }

    /**
     * 添加收藏
     * @param bookId 电子书id
     * @return 操作结果
     */
    @RequestMapping("/add")
    @RequireLogin
    public ResultInfo add(@NotNull Integer bookId)
    {
        favoriteService.add(bookId, getCurrentUser().getId());
        return ResultInfo.success();
    }

    /**
     * 取消收藏
     * @param bookId 电子书id
     * @return 操作结果
     */
    @RequestMapping("/cancel")
    @RequireLogin
    public ResultInfo cancel(@NotNull Integer bookId)
    {
        favoriteService.cancel(bookId, getCurrentUser().getId());
        return ResultInfo.success();
    }
}
