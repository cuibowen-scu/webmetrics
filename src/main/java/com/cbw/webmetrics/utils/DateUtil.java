package com.cbw.webmetrics.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * help date things
 */
public class DateUtil {

    /**
     * return the nano time
     */
    public static long getNanoTime() {
        return System.nanoTime();
    }

    /**
     * return time like 2018-12-07 16:47:50
     */
    public static String getUserTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * return time like 2018-12-07 16:47
     */
    public static String getUserTimeMin() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(new Date());
    }
}
