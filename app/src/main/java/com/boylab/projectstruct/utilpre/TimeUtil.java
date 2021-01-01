package com.boylab.projectstruct.utilpre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 复杂问题简单化
 * 按宏观天数计数
 */
public class TimeUtil {

    public static final String DF_DATE = "yyyy-MM-dd";
    public static final String DF_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DF_DATE_CHN = "yyyy年MM月dd日";
    public static final String DF_TIME_CHN = "yyyy年MM月dd日 HH:mm:ss";

    public Calendar getCalendar(String format, String formatTime){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat(format);
            date = timeFormat.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    public String getTime(Calendar calendar, String format){
        SimpleDateFormat timeFormat = new SimpleDateFormat(format);
        String dateTime = timeFormat.format(calendar.getTime());
        return dateTime;
    }

    public int awayNow(Calendar mCalendar){
        Calendar nowCalendar = Calendar.getInstance();
        int gapDays = gapDays(nowCalendar, mCalendar);
        return gapDays;
    }

    public int gapDays(Calendar reCalendar, Calendar preCalendar){
        int nowDays = reCalendar.get(Calendar.DAY_OF_YEAR);
        int nowYear = reCalendar.get(Calendar.YEAR);

        int freshDays = preCalendar.get(Calendar.DAY_OF_YEAR);
        int freshYear = preCalendar.get(Calendar.YEAR);

        int awayDays = 0;
        if (nowYear > freshYear){
            for (int i = freshYear; i < nowYear; i++) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, i);
                awayDays = awayDays + cal.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            awayDays = nowDays + awayDays - freshDays;
        }else if (nowYear == freshYear){
            awayDays = nowDays - freshDays;
        }else {
            for (int i = nowYear; i < freshYear; i++) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, i);
                awayDays = awayDays + cal.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            awayDays = freshDays + awayDays - nowDays;
            awayDays = -awayDays;
        }
        return awayDays;
    }

}
