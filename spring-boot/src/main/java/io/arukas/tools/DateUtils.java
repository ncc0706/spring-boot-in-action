package io.arukas.tools;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static String getFormatDate(String strDate) {
        DateTimeFormatter formatter;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDate);
        if (zonedDateTime.toLocalDate().equals(LocalDate.now())) {
            if (getOverMinutes(strDate) <= 1) {
                return "刚刚";
            } else {
                formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.CHINA);
                return zonedDateTime.format(formatter);
            }
        } else {
            if (zonedDateTime.toLocalDate().getYear() == LocalDate.now().getYear()) {
                formatter = DateTimeFormatter.ofPattern("MM-dd", Locale.CHINA);
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
            }
            return zonedDateTime.format(formatter);
        }
    }

    /**
     * 根据创建时间和总共时间得到当前剩余时间
     * @param createStr
     * @param totalMills
     * @return
     */
    public static long getLeftMills(String createStr, long totalMills) {
        long leftMills = totalMills - getOverMills(createStr);
        return leftMills >= 0 ? leftMills : 0;
    }
    public static long getLeftMinutes(String createStr, long totalMinutes) {
        long leftMinutes = totalMinutes - getOverMinutes(createStr);
        return leftMinutes >= 0 ? leftMinutes : 0;
    }
    public static long getLeftHours(String createStr, long totalHours) {
        long leftHours = totalHours - getOverHours(createStr);
        return leftHours >= 0 ? leftHours : 0;
    }

    /**
     * 根据跟定的时间得到到目前为止过了多少秒
     * @param time
     * @return
     */
    public static long getOverMills(String time){
        return getOverTime(time).toMillis();
    }
    public static long getOverMinutes(String time){
        return getOverTime(time).toMinutes();
    }
    public static long getOverHours(String time){
        return getOverTime(time).toHours();
    }
    private static Duration getOverTime(String time) {
        Instant timeInstant = ZonedDateTime.parse(time).toInstant();
        Instant now = Instant.now();
        return Duration.between(timeInstant, now);
    }

    /**
     * 将UNIX时间戳（秒级）格式化成ZoneDateTime的格式
     */
    public static String format2ZoneDateTimeFromSecond(long timestamp) {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of("Asia/Shanghai")).toString();
    }

}
