package io.arukas.tools;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class RelativeDateFormat {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    
    
    
    public static String format(String date) {

        //现在的时间  
        Integer nowYear =  Integer.parseInt(DateTime.now().toString("yyyy"));

        //时间格式化  
//        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        //传进来的时间解析  
        DateTime paraDate = DateTime.parse(date,format);//年月日 时分秒
        Integer paraYear = Integer.parseInt(paraDate.toString("yyyy"));//年  
        String paraDay = paraDate.toString("yyyy-MM-dd");


        DateTime nowDate = new DateTime().now();   //当前  
        Interval hours1 = new Interval(nowDate.minusHours(1), nowDate);
        Interval hours24 = new Interval(nowDate.minusHours(24), nowDate);
        Interval day = new Interval(nowDate.minusDays(4), nowDate);
        Interval years = new Interval(nowDate.minusYears(1), nowDate);


        if (hours1.contains(paraDate)) { //一小时以内显示几分钟前；  
            Interval minute  = new  Interval(paraDate, nowDate);
            int res = minute.toPeriod().getMinutes();
            if(res == 0 || res == 1 ){
                return "刚刚";
            }
            return res+"分钟前";
            // return "几分钟前";  
        } else if (hours24.contains(paraDate)) { //24小时内显示几小时前；  
            Interval hours  = new  Interval(paraDate, nowDate);
            int res = hours.toPeriod().getHours();
            return res+"小时前";
            //return "几小时前";  
        } else if (day.contains(paraDate)) { //超过24小时三天内（含三天）显示几天前；  
            Interval Day  = new  Interval(paraDate, nowDate);
            int res = Day.toPeriod().getDays();
            return res+"天前";
        }else if(paraYear < nowYear ){
            return paraDay;
        }else {
            return paraDate.toString("MM-dd");
        }
    }

    /**
     * 代码有bug
     * @param date
     * @return
     */
    public static String formatNotGood(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
