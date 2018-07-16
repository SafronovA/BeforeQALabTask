package com.epam.beforeLabTask.util;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public int getFirstNumber(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        return Integer.valueOf(matcher.group());
    }

    public int getCurrentDay (){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getSaturdayDate(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDayOfMonth = getCurrentDay();
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInJuly = 31;
        int nextSaturdayDate = 0;

        switch (currentDayOfWeek){
            case 1: nextSaturdayDate = currentDayOfMonth + 6;
            break;
            case 2: nextSaturdayDate = currentDayOfMonth + 5;
            break;
            case 3: nextSaturdayDate = currentDayOfMonth + 4;
            break;
            case 4: nextSaturdayDate = currentDayOfMonth + 3;
            break;
            case 5: nextSaturdayDate = currentDayOfMonth + 2;
            break;
            case 6: nextSaturdayDate = currentDayOfMonth + 1;
            break;
            case 7: nextSaturdayDate = currentDayOfMonth;
            break;
        }

        // add adequate month checking
        if (nextSaturdayDate>daysInJuly){
            nextSaturdayDate-= daysInJuly;
        }

        return nextSaturdayDate;
    }
}
