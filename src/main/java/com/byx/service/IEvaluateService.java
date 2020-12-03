package com.byx.service;

/**
 * 点评服务接口
 */
public interface IEvaluateService
{
    void like(int bookId, int userId);
    void cancelLike(int bookId, int userId);
    void dislike(int bookId, int userId);
    void cancelDislike(int bookId, int userId);
    boolean isLike(int bookId, int userId);
    boolean isDislike(int bookId, int userId);
}
