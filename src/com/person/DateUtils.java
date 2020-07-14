package com.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.person
 */
public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // 1.字符串转util下的Date
    public static java.util.Date strToUtil(String str) {
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2.util下的Date转sql的Date
    public static java.sql.Date utilToSql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    // 3.util下的Date转字符串
    public static String utilToStr(java.util.Date date) {
        return sdf.format(date);
    }

}
