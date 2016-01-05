package com.janosgyerik.tools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Create a (truncated) date object from year, month, day.
     * Throws IllegalArgumentException if the parameters don't form
     * a valid date, for example 2014, 2, 31 or 2014, 99, 99
     *
     * @param year  Year, for example 2014
     * @param month Month, for example 2 for February
     * @param day   Day, for example 31st (of December)
     * @return truncated date (without hours, minutes, seconds and others)
     */
    public static Date create(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        validate(year, month, day, calendar);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    private static void validate(int year, int month, int day, Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_MONTH) != day) {
            throw new IllegalArgumentException(String.format("Invalid day=%s for year=%s and month=%s", day, year, month));
        }
        if (calendar.get(Calendar.MONTH) != month - 1) {
            throw new IllegalArgumentException(String.format("Invalid month=%s for year=%s and day=%s", month, year, day));
        }
    }

    public static String formatYMD(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date fromYMD(String ymd) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(ymd);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getMonthShortName(Date date) {
        return new SimpleDateFormat("MMM").format(date);
    }

    public static String getMonthLongName(Date date) {
        return new SimpleDateFormat("MMMMM").format(date);
    }

    public static String getDayShortName(Date date) {
        return new SimpleDateFormat("E").format(date);
    }

    public static String getDayLongName(Date date) {
        return new SimpleDateFormat("EEEEE").format(date);
    }

    public static Date truncate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date today() {
        return truncate(new Date());
    }
}
