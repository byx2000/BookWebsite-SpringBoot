package com.byx.service.impl;

import com.byx.dao.ICommentDao;
import com.byx.domain.Comment;
import com.byx.domain.PageBean;
import com.byx.query.CommentQuery;
import com.byx.service.ICommentService;
import com.byx.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务实现类
 */
@Service
public class CommentServiceImpl implements ICommentService
{
    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<Comment> query(CommentQuery query)
    {
        return commentDao.query(query);
    }

    @Override
    public PageBean<Comment> queryByPage(CommentQuery query, int pageSize, int currentPage)
    {
        // 计算结果总数
        query.setLimit(null);
        query.setOffset(null);
        int totalCount = commentDao.count(query);

        // 获取查询结果
        query.setLimit(pageSize);
        query.setOffset(pageSize * (currentPage - 1));
        List<Comment> comments = commentDao.query(query);

        // 组装PageBean
        PageBean<Comment> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalCount(totalCount);
        int totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        pageBean.setData(comments);

        return pageBean;
    }

    @Override
    public void save(Comment comment)
    {
        // 设置评论时间为当前时间
        comment.setTime(DateUtils.now());
        commentDao.save(comment);
    }
}
