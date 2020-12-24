package com.boylab.projectstruct.util;

import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 结合 SharedPreferences 可进行使用天数控制
 */
public class TimerLock {

    private final int MAX_DAY = 999;
    private final String BASE_DATE = "1970-01-01";
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int useDays = MAX_DAY;
    private String endTime = BASE_DATE, freshTime = BASE_DATE;
    private Calendar endCalendar, freshCalendar ;

    public TimerLock() {
    }

    public TimerLock(int useDays, String endTime, String freshTime) {
        this.useDays = useDays;
        this.endTime = endTime;
        this.freshTime = freshTime;
    }

    public int getUseDays() {
        return useDays;
    }

    public void setUseDays(int useDays) {
        this.useDays = useDays;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFreshTime() {
        return freshTime;
    }

    public void setFreshTime(String freshTime) {
        this.freshTime = freshTime;
    }

    /**
     * 核心方法，判断定时关机天数
     * 防止修改系统时间
     */
    public void checkSelf(){
        if (freshTime.equals(BASE_DATE)) {
            freshTime = getTime(Calendar.getInstance());
        }
        endCalendar = getCalendar(endTime);
        freshCalendar = getCalendar(freshTime);

        int awayDays = awayNow(freshCalendar);

        if (useDays > 0 && useDays < MAX_DAY){
            if (awayDays > 0){
                useDays = useDays - awayDays;
                useDays = useDays > 0 ? useDays : 0;
            }
        }
        freshCalendar = Calendar.getInstance();
        freshTime = getTime(freshCalendar);

        //重新计算截止日期
        if (useDays > 0 && useDays < MAX_DAY){
            endCalendar = Calendar.getInstance();
            endCalendar.add(Calendar.DATE, useDays);
            endTime = getTime(endCalendar);
        }
    }

    private int awayNow(Calendar mCalendar){
        Calendar nowCalendar = Calendar.getInstance();

        int nowDays = nowCalendar.get(Calendar.DAY_OF_YEAR);
        int nowYear = nowCalendar.get(Calendar.YEAR);

        int freshDays = mCalendar.get(Calendar.DAY_OF_YEAR);
        int freshYear = mCalendar.get(Calendar.YEAR);

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

    private Calendar getCalendar(String dateTime){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = timeFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    private String getTime(Calendar calendar){
        String dateTime = timeFormat.format(calendar.getTime());
        return dateTime;
    }

    @Override
    public String toString() {
        return "TimingControl{" +
                "useDays=" + useDays +
                ", endTime='" + endTime + '\'' +
                ", freshTime='" + freshTime + '\'' +
                ", endCalendar=" + endCalendar +
                ", freshCalendar=" + freshCalendar +
                ", simpleDateFormat=" + timeFormat +
                '}';
    }
}
