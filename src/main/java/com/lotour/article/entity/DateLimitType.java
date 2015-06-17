package com.lotour.article.entity;

/**
 * Created by ljing on 2015/5/13.
 * 日期限制类型
 */
public enum DateLimitType
{
    /**
     * 无限制
     */
    NONE,
    /**
     * 本日
     */
    TODAY,
    /**
     * 本周
     */
    INAWEEK,
    /**
     * 本月
     */
    INAMONTH,
    /**
     * 本季
     */
    INASEASON,
    /**
     * 本年
     */
    INAYEAR,
}
