package com.gcg.readingisgood.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.spi.CalendarDataProvider;

public class DateUtil {

    public static Date today(){
        return new Date();
    }

    public static Date stringToDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
    }

    public static Date getMonthFirstDay(String month) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateUtil.today());
        Integer year = calendar.getWeekYear();
        return stringToDate("01-" + month + "-" + year.toString());
    }

    public static Date getMonthLastDay(String month) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateUtil.today());
        Integer year = calendar.getWeekYear();

        if (is31Month(Integer.parseInt(month)))
            return stringToDate("30-" + month + "-" + year);
        else
            return stringToDate("31-" + month + "-" + year);
    }

    public static Boolean is31Month(Integer month){
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return true;
                default:
                    return false;
        }
    }

}
