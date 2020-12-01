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
public class BookController extends BaseController
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
}
