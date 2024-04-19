package com.jatismobile.unittest.utils;

import java.util.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTimeUtilTests {

    @Test
    void constructor() throws Exception {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        Assertions.assertTrue(dateTimeUtil instanceof DateTimeUtil);
    }

    @Test
    void dateToString() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, 3, 15, 0, 0, 0);
        String dateString = DateTimeUtil.dateToString(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, calendar.getTime());

        Assertions.assertEquals("2024-04-15 00:00:00", dateString);
    }

    @Test
    void stringToDate() throws Exception {
        Calendar calendarExpected = Calendar.getInstance();
        calendarExpected.set(2024, 3, 15, 0, 0, 0);

        Date date = DateTimeUtil.stringToDate(DateTimeUtil.YYYY_MM_DD_HH_MM_SS, "2024-04-15 00:00:00");
        Calendar calendarResult = Calendar.getInstance();
        calendarResult.setTime(date);

        Assertions.assertEquals(calendarExpected.get(Calendar.YEAR), calendarResult.get(Calendar.YEAR));
        Assertions.assertEquals(calendarExpected.get(Calendar.MONTH), calendarResult.get(Calendar.MONTH));
        Assertions.assertEquals(calendarExpected.get(Calendar.DAY_OF_MONTH), calendarResult.get(Calendar.DAY_OF_MONTH));
    }
}
