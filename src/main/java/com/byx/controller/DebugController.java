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
        /*List<Book> books = jdbcTemplate.query("SELECT * FROM books WHERE categoryId = 1",
                new BeanPropertyRowMapper<>(Book.class));
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b);
        }*/

        /*BookQuery query = new BookQuery();
        query.setCategoryId(3);
        int count = bookDao.count(query);
        System.out.println(count);*/

        /*BookQuery query = new BookQuery();
        query.setCategoryId(3);
        List<Book> books = bookDao.query(query);
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b.getName());
        }*/

        /*List<Book> books = bookDao.getRandomBooks(5);
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b.getName());
        }*/

        /*List<Book> books = bookDao.getSearchSuggestion("妻子", 5);
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b.getName());
        }*/

        /*List<Book> books = bookDao.getSimilarRecommend(1, 5);
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b.getName());
        }*/

        /*CategoryQuery query = new CategoryQuery();
        query.setCategoryId(4);
        List<Category> categories = categoryDao.query(query);
        for (Category c : categories)
        {
            System.out.println(c);
        }*/

        /*UserQuery query = new UserQuery();
        List<User> users = userDao.query(query);
        System.out.println(users.size());
        for (User u : users)
        {
            System.out.println(u);
        }*/

        /*CommentQuery query = new CommentQuery();
        query.setBookId(1);
        int cnt = commentDao.count(query);
        System.out.println(cnt);*/

        /*CommentQuery query = new CommentQuery();
        query.setBookId(1);
        List<Comment> comments = commentDao.query(query);
        System.out.println(comments.size());
        for (Comment c : comments)
        {
            System.out.println(c);
        }*/

        /*Comment comment = new Comment();
        comment.setBookId(1);
        comment.setUserId(1);
        comment.setContent("哈哈");
        comment.setTime(DateUtils.now());
        int cnt = commentDao.save(comment);
        System.out.println(cnt);*/

        /*BookQuery query = new BookQuery();
        query.setCategoryId(3);
        List<Book> books = bookService.query(query);
        System.out.println(books.size());
        for (Book b : books)
        {
            System.out.println(b.getName());
        }*/

        BookQuery query = new BookQuery();
        query.setCategoryId(3);
        PageBean<Book> pageBean = bookService.queryByPage(query, 10, 3);
        System.out.println(pageBean.getData().size());
        for (Book b : pageBean.getData())
        {
            System.out.println(b.getName());
        }

        return "该接口用于后端调试";
    }

    @RequestMapping("/json")
    @ResponseBody
    public ResultInfo json()
    {
        CommentQuery query = new CommentQuery();
        query.setBookId(1);
        query.setUserId(1);
        List<Comment> comments = commentDao.query(query);
        System.out.println(comments.size());
        for (Comment c : comments)
        {
            System.out.println(c);
        }
        return ResultInfo.success(null);
    }
}