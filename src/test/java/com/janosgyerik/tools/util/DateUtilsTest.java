package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    @Test
    public void test_create_2014_12_03() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("2014-12-03", DateUtils.formatYMD(date));
        assertEquals("Wed Dec 03 00:00:00 CET 2014", date.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_02_31() {
        DateUtils.create(2014, 2, 31);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_02_m1() {
        DateUtils.create(2014, 2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_2014_13_01() {
        DateUtils.create(2014, 13, 1);
    }

    @Test
    public void test_getMonthShortName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Dec", DateUtils.getMonthShortName(date));
    }

    @Test
    public void test_getMonthLongName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("December", DateUtils.getMonthLongName(date));
    }

    @Test
    public void test_getDayShortName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Wed", DateUtils.getDayShortName(date));
    }

    @Test
    public void test_getDayLongName() {
        Date date = DateUtils.create(2014, 12, 3);
        assertEquals("Wednesday", DateUtils.getDayLongName(date));
    }

    @Test
    public void test_truncate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.truncate(new Date()));
        assertEquals(0, calendar.get(Calendar.SECOND));
        assertEquals(0, calendar.get(Calendar.MINUTE));
        assertEquals(0, calendar.get(Calendar.HOUR));
        assertEquals(0, calendar.get(Calendar.MILLISECOND));
    }

    @Test
    public void test_fromYMD() {
        assertEquals(DateUtils.create(2014, 12, 8), DateUtils.fromYMD("2014-12-08"));
    }
}
