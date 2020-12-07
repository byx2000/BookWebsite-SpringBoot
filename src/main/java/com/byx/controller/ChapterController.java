package com.byx.controller;

import com.byx.domain.ResultInfo;
import com.byx.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电子书章节控制器
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController
{
    @Autowired
    private IChapterService chapterService;

    /**
     * 获取章节数
     * @param bookId 电子书id
     * @return 章节数
     */
    @RequestMapping("/count")
    public ResultInfo count(Integer bookId)
    {
        // 参数校验
        if (bookId == null) return ResultInfo.fail("参数错误");

        return ResultInfo.success(chapterService.getChapterCount(bookId));
    }

    /**
     * 获取章节数据
     * @param bookId 电子书id
     * @param chapter 章节
     * @return {Text, Book}
     */
    @RequestMapping("/data")
    public ResultInfo chapter(Integer bookId, Integer chapter)
    {
        // 参数校验
        if (bookId == null || chapter == null) return ResultInfo.fail("参数错误");

        return ResultInfo.success(chapterService.getChapter(bookId, chapter));
    }
}
