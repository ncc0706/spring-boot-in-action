package io.arukas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/11/30 17:20 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public class CalendarPropertySource extends PropertySource<Calendar> {

    private static final Log logger = LogFactory.getLog(CalendarPropertySource.class);

    public static final String DATE_PROPERTY_SOURCE_NAME = "date_time";

    public static final String PREFIX = "date.";

    public static final String NOW = "NOW";
    public static final String TIMESTAMP = "TIMESTAMP";
    //yyyy-MM-dd HH:mm:ss
    public static final String FORMAT = "FORMAT";
    public static final String YMD = "YMD";
    public static final String HMS = "HMS";
    public static final String ERA = "ERA";
    public static final String YEAR = "YEAR";
    public static final String DAY_OF_YEAR = "DAY_OF_YEAR";
    public static final String DAY_OF_MONTH = "DAY_OF_MONTH";
    public static final String WEEK_OF_YEAR = "WEEK_OF_YEAR";
    public static final String WEEK_OF_MONTH = "WEEK_OF_MONTH";
    public static final String DAY_OF_WEEK_IN_MONTH = "DAY_OF_WEEK_IN_MONTH";
    public static final String AM_PM = "AM_PM";
    public static final String HOUR = "HOUR";
    public static final String HOUR_OF_DAY = "HOUR_OF_DAY";
    public static final String MINUTE = "MINUTE";
    public static final String SECOND = "SECOND";
    public static final String MILLISECOND = "MILLISECOND";

    public CalendarPropertySource(String name) {
        super(name, Calendar.getInstance());
    }

    public CalendarPropertySource() {
        this(DATE_PROPERTY_SOURCE_NAME);
    }

    @Override
    public Object getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Generating date property for '" + name + "'");
        }
        String valueType = name.substring(PREFIX.length()).toUpperCase();
        Calendar now = Calendar.getInstance();
        switch (valueType) {
            case NOW:
                return getNow(now);
            case TIMESTAMP:
                return getTimestamp(now);
            case FORMAT:
                return getFormat(now);
            case YMD:
                return getYMD(now);
            case HMS:
                return getHMS(now);
            case ERA:
                return getEra(now);
            case YEAR:
                return getYear(now);
            case DAY_OF_YEAR:
                return getDayOfYear(now);
            case DAY_OF_MONTH:
                return getDayOfMonth(now);
            case WEEK_OF_YEAR:
                return getWeekOfYear(now);
            case WEEK_OF_MONTH:
                return getWeekOfMonth(now);
            case DAY_OF_WEEK_IN_MONTH:
                return getDayOfWeekInMonth(now);
            case AM_PM:
                return getAmOrPm(now);
            case HOUR:
                return getHour(now);
            case HOUR_OF_DAY:
                return getHourOfDay(now);
            case MINUTE:
                return getMinute(now);
            case SECOND:
                return getSecond(now);
            case MILLISECOND:
                return getMilliSecond(now);
            default:
                return getNow(now);
        }
    }

    public static void addToEnvironment(ConfigurableEnvironment environment) {
        environment.getPropertySources().addAfter(
                StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
                new CalendarPropertySource(DATE_PROPERTY_SOURCE_NAME));
        logger.trace("RandomValuePropertySource add to Environment");
    }

    private Date getNow(Calendar instance) {
        return instance.getTime();
    }

    private String getFormat(Calendar instance) {
        StringBuffer sb = new StringBuffer(64);
        sb.append(instance.get(Calendar.YEAR))
                .append("-")
                .append(instance.get(Calendar.MONTH))
                .append("-")
                .append(instance.get(Calendar.DAY_OF_MONTH))
                .append(" ")
                .append(instance.get(Calendar.HOUR))
                .append(":")
                .append(instance.get(Calendar.MINUTE))
                .append(":")
                .append(instance.get(Calendar.SECOND));
        return sb.toString();
    }

    private long getTimestamp(Calendar instance) {
        return instance.getTimeInMillis();
    }

    private String getYMD(Calendar instance) {
        return instance.get(Calendar.YEAR) + "-" + instance.get(Calendar.MONTH) + "-" + instance.get(Calendar.DAY_OF_MONTH);
    }

    private String getHMS(Calendar instance) {
        return instance.get(Calendar.HOUR_OF_DAY) + ":" + instance.get(Calendar.MINUTE) + ":" + instance.get(Calendar.SECOND);
    }

    private int getEra(Calendar instance) {
        return instance.get(Calendar.ERA);
    }

    private int getYear(Calendar instance) {
        return instance.get(Calendar.YEAR);
    }

    private int getDayOfYear(Calendar instance) {
        return instance.get(Calendar.DAY_OF_YEAR);
    }

    private int getDayOfMonth(Calendar instance) {
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    private int getWeekOfYear(Calendar instance) {
        return instance.get(Calendar.WEEK_OF_YEAR);
    }

    private int getWeekOfMonth(Calendar instance) {
        return instance.get(Calendar.WEEK_OF_MONTH);
    }

    private int getDayOfWeek(Calendar instance) {
        return instance.get(Calendar.DAY_OF_WEEK);
    }

    private int getDayOfWeekInMonth(Calendar instance) {
        return instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    private int getAmOrPm(Calendar instance) {
        return instance.get(Calendar.AM_PM);
    }

    private int getHour(Calendar instance) {
        return instance.get(Calendar.HOUR);
    }

    private int getHourOfDay(Calendar instance) {
        return instance.get(Calendar.HOUR_OF_DAY);
    }

    private int getMinute(Calendar instance) {
        return instance.get(Calendar.MINUTE);
    }

    private int getSecond(Calendar instance) {
        return instance.get(Calendar.SECOND);
    }

    private int getMilliSecond(Calendar instance) {
        return instance.get(Calendar.MILLISECOND);
    }
}
