package com.byx.controller;

import com.byx.dao.*;
import com.byx.domain.*;
import com.byx.query.*;
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

    @Autowired
    private IFavoriteDao favoriteDao;

    /**
     * 后端调试接口
     * @return 说明文字
     */
    @RequestMapping("")
    @ResponseBody
    public String debug()
    {
        BookQuery query = new BookQuery();
        query.setLimit(5);


        List<Book> books = bookDao.query(query);

        System.out.println(books.size());
        System.out.println(books);

        return "该接口用于后端调试";
    }
}