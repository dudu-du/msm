package com.welsee.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 获取精确到秒的时间戳(1541729719)
     *
     * @return int
     */
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }


    /**
     * 获取今天的日期(20181105)
     *
     * @return String
     */
    public static String getDateFormat() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
        return dateFormat.format(now);
    }


    /**
     * 获取精确到秒的时间戳(201811091120345)
     *
     * @return String
     */
    public static String getSecondTimeDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    public static boolean compare(Date d1, Date d2) {
        if (d1.compareTo(d2) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前年份
     *
     * @return String
     */
    public static String getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentYear());
    }

}
