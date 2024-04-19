package com.jatismobile.unittest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd' 'HH:mm:ss";

    public static String dateToString(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String format, String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }
}
