package com.byx.query;

import java.util.List;

/**
 * 查询接口
 */
public interface IQuery
{
    /**
     * 获取查询子句字符串(WHERE...ORDER BY...LIMIT...OFFSET...)
     * @return 查询子句字符串
     */
    String getQueryString();

    /**
     * 获取sql参数
     * @return 参数列表
     */
    List<Object> getParameters();
}
