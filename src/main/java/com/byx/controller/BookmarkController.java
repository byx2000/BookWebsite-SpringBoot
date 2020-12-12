package com.byx.controller;

import com.byx.annotation.RequireLogin;
import com.byx.domain.ResultInfo;
import com.byx.service.IBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 书签控制器
 */
@RestController
@RequestMapping("/bookmark")
@Validated
public class BookmarkController extends BaseController
{
    @Autowired
    private IBookmarkService bookmarkService;

    /**
     * 添加书签
     * @param bookId 电子书id
     * @param chapter 章节
     * @return 操作结果
     */
    @RequestMapping("/add")
    @RequireLogin
    public ResultInfo add(@NotNull Integer bookId,
                          @NotNull Integer chapter)
    {
        bookmarkService.add(getCurrentUser().getId(), bookId, chapter);
        return ResultInfo.success();
    }

    /**
     * 移除书签
     * @param bookId 电子书id
     * @param chapter 章节id
     * @return 操作结果
     */
    @RequestMapping("/remove")
    @RequireLogin
    public ResultInfo remove(@NotNull Integer bookId,
                             @NotNull Integer chapter)
    {
        bookmarkService.remove(getCurrentUser().getId(), bookId, chapter);
        return ResultInfo.success();
    }

    /**
     * 是否添加了书签
     * @param bookId 电子书id
     * @param chapter 章节id
     * @return 如果已添加书签，返回true，否则返回false
     */
    @RequestMapping("/is_bookmark")
    @RequireLogin
    public ResultInfo isBookmark(@NotNull Integer bookId,
                                 @NotNull Integer chapter)
    {
        return ResultInfo.success(bookmarkService.isBookmark(getCurrentUser().getId(), bookId, chapter));
    }
}
