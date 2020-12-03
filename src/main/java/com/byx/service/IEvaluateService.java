package com.byx.service;

/**
 * 点赞服务接口
 */
public interface IEvaluateService
{
    void like(int bookId, int userId);
    void cancelLike(int bookId, int userId);
    void dislike(int bookId, int userId);
    void cancelDislike(int bookId, int userId);
}
