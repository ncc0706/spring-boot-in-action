package io.arukas.tools;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestDateUtils {

    @Test
    public void test01() {


//        Instant now = Instant.now();
//        System.out.println(now.getEpochSecond());

        ZonedDateTime now = ZonedDateTime.now();
        String strDate = now.toString();
        System.out.println(strDate);

        String formatDate = DateUtils.getFormatDate(strDate);
        System.out.println(formatDate);

    }

    @Test
    public void test02() throws ParseException {

        String date = "2018-02-20 00:30:35";

        System.out.println(RelativeDateFormat.format(date));
    }

}
