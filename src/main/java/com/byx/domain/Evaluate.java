package com.byx.domain;

/**
 * 点评实体类
 * <p>对应于数据库中的evaluates表</p>
 */
public class Evaluate {
    /**
     * 点评id
     */
    private Integer id;

    /**
     * 电子书id
     */
    private Integer bookId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 点评状态：0表示已赞，1表示已踩
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", state=" + state +
                '}';
    }
}
