package com.byx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期工具类
 */
public class DateUtils {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间
     *
     * @return 时间的字符串形式
     */
    public static String now() {
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }

    /**
     * 获取指定天数前的时间
     *
     * @param day 天数
     * @return 时间的字符串形式
     */
    public static String daysAgo(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -day);
        return format.format(calendar.getTime());
    }
}
