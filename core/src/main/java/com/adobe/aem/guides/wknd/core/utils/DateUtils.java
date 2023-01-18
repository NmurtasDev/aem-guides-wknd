package com.adobe.aem.guides.wknd.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getDateOnTheFuture(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,i);
        return calendar.getTime();
    }

    public static SimpleDateFormat getSimpleDateFormatForDatePicker() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
