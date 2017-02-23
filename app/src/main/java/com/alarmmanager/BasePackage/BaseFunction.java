package com.alarmmanager.BasePackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Suprada on 23-Feb-17.
 */

public class BaseFunction {
    public static String showTime(int hour, int min) {
        String AMPM="AM";
        if (hour == 0) {
            hour += 12;
            AMPM = "AM";
        } else if (hour == 12) {
            AMPM = "PM";
        } else if (hour > 12) {
            hour -= 12;
            AMPM = "PM";
        } else {
            AMPM = "AM";
        }
        Date dateObj = new Date();
        String time = hour + ":" + min;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            dateObj = sdf.parse(time);
            System.out.println(dateObj);
            System.out.println(new SimpleDateFormat("HH:mm").format(dateObj));

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("HH:mm").format(dateObj) + ":" + AMPM;
    }
}
