package com.byx.controller;

import com.byx.dao.IBookDao;
import com.byx.dao.ICategoryDao;
import com.byx.dao.ICommentDao;
import com.byx.dao.IUserDao;
import com.byx.domain.*;
import com.byx.query.BookQuery;
import com.byx.query.CategoryQuery;
import com.byx.query.CommentQuery;
import com.byx.query.UserQuery;
import com.byx.service.IBookService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 调试控制器
 */
@Controller
@RequestMapping("/debug")
public class DebugController
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private ICategoryDao categoryDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private IBookService bookService;

    /**
     * 后端调试接口
     * @return 说明文字
     */
    @RequestMapping("")
    @ResponseBody
    public String debug()
    {
        List<Book> books = bookService.getRandomBooks(5);
        for (Book b : books)
        {
            System.out.println(b.getName());
        }

        return "该接口用于后端调试";
    }
}