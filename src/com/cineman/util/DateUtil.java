package com.cineman.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility class for date and time operations
 */
public class DateUtil {

    // Date format patterns
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";
    private static final String DISPLAY_TIME_FORMAT = "HH:mm";

    /**
     * Get current SQL Date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Get current SQL Time
     */
    public static Time getCurrentTime() {
        return new Time(System.currentTimeMillis());
    }

    /**
     * Get current Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Convert String to SQL Date (format: yyyy-MM-dd)
     */
    public static Date stringToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            java.util.Date utilDate = sdf.parse(dateStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + dateStr);
            return null;
        }
    }

    /**
     * Convert String to SQL Time (format: HH:mm:ss)
     */
    public static Time stringToTime(String timeStr) {
        if (timeStr == null || timeStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            sdf.setLenient(false);
            java.util.Date utilDate = sdf.parse(timeStr);
            return new Time(utilDate.getTime());
        } catch (ParseException e) {
            System.err.println("Error parsing time: " + timeStr);
            return null;
        }
    }

    /**
     * Convert SQL Date to String (format: yyyy-MM-dd)
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Convert SQL Time to String (format: HH:mm:ss)
     */
    public static String timeToString(Time time) {
        if (time == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(time);
    }

    /**
     * Format date for display (format: dd/MM/yyyy)
     */
    public static String formatDateForDisplay(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Format time for display (format: HH:mm)
     */
    public static String formatTimeForDisplay(Time time) {
        if (time == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DISPLAY_TIME_FORMAT);
        return sdf.format(time);
    }

    /**
     * Add days to a date
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Add months to a date
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Add years to a date
     */
    public static Date addYears(Date date, int years) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Check if date1 is before date2
     */
    public static boolean isBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.before(date2);
    }

    /**
     * Check if date1 is after date2
     */
    public static boolean isAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.after(date2);
    }

    /**
     * Validate date string format (yyyy-MM-dd)
     */
    public static boolean isValidDateFormat(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Validate time string format (HH:mm:ss or HH:mm)
     */
    public static boolean isValidTimeFormat(String timeStr) {
        if (timeStr == null || timeStr.trim().isEmpty()) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            sdf.setLenient(false);
            sdf.parse(timeStr);
            return true;
        } catch (ParseException e) {
            // Try shorter format
            try {
                SimpleDateFormat sdf2 = new SimpleDateFormat(DISPLAY_TIME_FORMAT);
                sdf2.setLenient(false);
                sdf2.parse(timeStr);
                return true;
            } catch (ParseException e2) {
                return false;
            }
        }
    }
}
