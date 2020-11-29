package com.byx.controller;

import com.byx.domain.Book;
import com.byx.domain.PageBean;
import com.byx.domain.ResultInfo;
import com.byx.query.BookQuery;
import com.byx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    private IBookService bookService;

    /**
     * 根据条件查询电子书
     * @param bookQuery 查询条件
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     * @return 结果
     */
    @RequestMapping("/query")
    public ResultInfo query(BookQuery bookQuery, Integer pageSize, Integer currentPage)
    {
        // 不带分页的查询
        if (pageSize == null || currentPage == null)
        {
            List<Book> books = bookService.query(bookQuery);
            return ResultInfo.success(books);
        }
        // 带分页的查询
        else
        {
            PageBean<Book> pageBean = bookService.queryByPage(bookQuery, pageSize, currentPage);
            return ResultInfo.success(pageBean);
        }
    }

    /**
     * 获取随机电子书
     * @param count 数量
     * @return 结果
     */
    @RequestMapping("/random")
    public ResultInfo random(Integer count)
    {
        // 参数校验
        if (count == null) return ResultInfo.fail("参数错误");

        List<Book> books = bookService.getRandomBooks(count);
        return ResultInfo.success(books);
    }

    /**
     * 搜索建议
     * @param keyword 关键词
     * @param count 数量
     * @return 结果
     */
    @RequestMapping("/searchSuggestion")
    public ResultInfo searchSuggestion(String keyword, Integer count)
    {
        // 参数校验
        if (keyword == null || count == null) return ResultInfo.fail("参数错误");

        List<Book> books = bookService.getSearchSuggestion(keyword, count);
        return ResultInfo.success(books);
    }

    /**
     * 同类推荐
     * @param categoryId 类型id
     * @param count 数量
     * @return 结果
     */
    @RequestMapping("/similarRecommend")
    public ResultInfo similarRecommend(Integer categoryId, Integer count)
    {
        // 参数校验
        if (categoryId == null || count == null) return ResultInfo.fail("参数错误");

        List<Book> books = bookService.getSimilarRecommend(categoryId, count);
        return ResultInfo.success(books);
    }
}
