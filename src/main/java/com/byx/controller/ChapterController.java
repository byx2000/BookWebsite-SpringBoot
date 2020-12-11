package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 电子书章节控制器
 */
@RestController
@RequestMapping("/chapter")
@Validated
public class ChapterController extends BaseController
{
    @Autowired
    private IChapterService chapterService;

    /**
     * 获取章节数
     * @param bookId 电子书id
     * @return 章节数
     */
    @RequestMapping("/count")
    public ResultInfo count(@NotNull Integer bookId)
    {
        // 登录校验
        if (getCurrentUser() == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(chapterService.getChapterCount(bookId));
    }

    /**
     * 获取章节数据
     * @param bookId 电子书id
     * @param chapter 章节
     * @return {Text, Book}
     */
    @RequestMapping("/data")
    public ResultInfo chapter(@NotNull Integer bookId,
                              @NotNull Integer chapter)
    {
        // 登录校验
        if (getCurrentUser() == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(chapterService.getChapterAndBook(bookId, chapter));
    }

    /**
     * 获取电子书目录
     * @param bookId 电子书id
     * @return [Chapter, ...]
     */
    @RequestMapping("/contents")
    public ResultInfo contents(@NotNull Integer bookId)
    {
        // 登录校验
        if (getCurrentUser() == null) return ResultInfo.fail("当前未登录");

        return ResultInfo.success(chapterService.getContents(bookId));
    }
}
