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
}
