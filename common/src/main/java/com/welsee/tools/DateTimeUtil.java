package com.welsee.tools;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.welsee.exception.ProgramException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    public static final String DAY_FORMAT = "yyyy-MM-dd";
    public static final String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getLocalDate(LocalDate fromDate) {
        return getLocalDate(fromDate, DAY_FORMAT);
    }

    /**
     * LocalDate转化为指定格式字符串
     *
     * @param fromDate
     * @param dateFormat
     * @return
     */
    public static String getLocalDate(LocalDate fromDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        if (fromDate != null) {
            String dateStr = fromDate.format(df);
            return dateStr;
        }
        return null;

    }

    public static String getLocalDateTime(LocalDateTime fromDateTime) {
        return getLocalDateTime(fromDateTime, FULL_FORMAT);
    }

    /**
     * LocalDateTime转化为指定格式字符串
     *
     * @param fromDateTime
     * @param dateTimeFotmat
     * @return
     */
    public static String getLocalDateTime(LocalDateTime fromDateTime, String dateTimeFotmat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFotmat);
        if (fromDateTime != null) {
            String localTime = fromDateTime.format(df);
            return localTime;
        }
        return null;

    }

    public static LocalDate fromString2LocalDate(String beginDate) {
        return fromString2LocalDate(beginDate, DAY_FORMAT);
    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @param beginDate
     * @param dateFormat
     * @return
     */
    public static LocalDate fromString2LocalDate(String beginDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate fromDate = LocalDate.parse(beginDate, df);
            return fromDate;
        } catch (Exception e) {
            return null;
        }

    }

    public static LocalDateTime fromString2LocalDateTime(String beginDateTime) {
        return fromString2LocalDateTime(beginDateTime, FULL_FORMAT);
    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @param beginDateTime
     * @param dateFormat
     * @return
     */
    public static LocalDateTime fromString2LocalDateTime(String beginDateTime, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(beginDateTime, df);
            return fromDateTime;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static String getMilliSecondTimestamp() {

        return String.valueOf(new Date().getTime());
    }


    /**
     * Date类型转LocalDate类型
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localPriceDate = instant.atZone(zoneId).toLocalDate();
        return localPriceDate;
    }

    /**
     * LocalDate类型转Date类型
     *
     * @param localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 判断日期是否是周末
     *
     * @param bDate
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(LocalDate bDate) throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(bDate);
        if (bDate.getDayOfWeek().getValue() == Calendar.SATURDAY || bDate.getDayOfWeek().getValue() == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个日期是否在某一段日期段内
     *
     * @param nowDate   查询的日期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @throws Exception
     */
    public static boolean yearMonthBetween(String nowDate, String startDate, String endDate) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date now = format.parse(nowDate);
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);

        long nowTime = now.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();

        return nowTime >= startTime && nowTime <= endTime;
    }

    public static void main(String[] args) throws Exception {
        String nowDate = "2019-05-12";
        String startDate = "2019-05-08";
        String endDate = "2019-05-11";
        System.out.println(yearMonthBetween(nowDate,startDate,endDate));
    }

    /**
     * 判断是否是上午
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static boolean isMorning(String time) throws Exception {
        boolean result = false;
        if (StringUtils.isEmpty(time)) {
            throw new ProgramException("时间错误");
        }
        // "2018-01-11 06:00:00"; 时间格式
        String str = time.substring(11, 13); // "06:00:00"; 时间格式
        int a = Integer.parseInt(str);
        if (a <= 12) {
            result = true;
        }
        return result;
    }

    /**

     * 获得指定日期的后一天

     * @param specifiedDay

     * @return

     */

    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);
        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

}
