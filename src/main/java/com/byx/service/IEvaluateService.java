package com.byx.service;

/**
 * 点评服务接口
 */
public interface IEvaluateService {
    /**
     * 点赞
     *
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void like(int bookId, int userId);

    /**
     * 取消点赞
     *
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void cancelLike(int bookId, int userId);

    /**
     * 点踩
     *
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void dislike(int bookId, int userId);

    /**
     * 取消点踩
     *
     * @param bookId 电子书id
     * @param userId 用户id
     */
    void cancelDislike(int bookId, int userId);

    /**
     * 是否已点赞
     *
     * @param bookId 电子书id
     * @param userId 用户id
     * @return true或false
     */
    boolean isLike(int bookId, int userId);

    /**
     * 是否已点踩
     *
     * @param bookId 电子书id
     * @param userId 用户id
     * @return true或false
     */
    boolean isDislike(int bookId, int userId);
}
