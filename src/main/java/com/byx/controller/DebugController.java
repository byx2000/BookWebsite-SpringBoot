package com.byx.controller;

import com.byx.dao.*;
import com.byx.domain.PageBean;
import com.byx.domain.User;
import com.byx.query.FavoriteQuery;
import com.byx.service.IBookService;
import com.byx.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 调试控制器
 */
@Controller
@RequestMapping("/debug")
public class DebugController extends BaseController
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

    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 后端调试接口
     * @return 说明文字
     */
    @RequestMapping("")
    @ResponseBody
    public String debug()
    {
        User user = new User();
        user.setUsername("def");
        user.setPassword("123");
        user.setNickname("白宇轩");

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("users")
                .usingGeneratedKeyColumns("id")
                .execute(new BeanPropertySqlParameterSource(user));

        return "该接口用于后端调试";
    }
}