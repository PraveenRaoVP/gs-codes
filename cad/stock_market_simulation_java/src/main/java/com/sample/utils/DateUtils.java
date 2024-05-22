package com.sample.utils;

import java.sql.Date;
import java.util.Calendar;

public class DateUtils {
    public static boolean validateAge(String dob) {
        // get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        String[] dobParts = dob.split("-");
        int year = Integer.parseInt(dobParts[0]);
        int month = Integer.parseInt(dobParts[1]);
        int day = Integer.parseInt(dobParts[2]);

        int age = currentYear - year;
        if (currentMonth < month || (currentMonth == month && currentDay < day)) {
            age--;
        }
        return age >= 18;
    }

    public static Date returnDobAsDate(String date) {
        // return the date in milliseconds
        return Date.valueOf(date);
    }
}
