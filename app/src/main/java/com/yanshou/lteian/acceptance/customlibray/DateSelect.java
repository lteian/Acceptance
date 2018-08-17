package com.yanshou.lteian.acceptance.customlibray;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateSelect {

    private final static int DATE_TODAY = 1, DATE_YESTERDAY =2, DATE_LAST_MONTH =3, DATE_LONG_TIME_AGO = 4;


    /**
     * 获取今日0时0分的时间戳
     * @return Long
     * @author lteian
     */
    private Long findToday (){
        Date now = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        long startTime = cal.getTimeInMillis();
        return startTime;
    }

    /**
     * 获取昨日0时0分时间戳
     * @return Long
     * @author lteian
     */
    private Long findYesterday(){
        return findToday()-24*60*60*1000;
    }

    private Long findLastMonthStart(){
        Date now = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_MONTH,0);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        long startTime = cal.getTimeInMillis();
        return startTime;
    }

    /**
     * 获取startTime
     * @param dateSelect
     * @return Long
     * @author lteian
     */
    public Long findStartDate (int dateSelect){
        Long startTime = null;
        switch (dateSelect){
            case DATE_TODAY:
                startTime = findToday();
                break;
            case DATE_YESTERDAY:
                startTime = findToday()-24*60*60*1000;
                break;
            case DATE_LAST_MONTH:
                startTime =findLastMonthStart();
                break;
            case DATE_LONG_TIME_AGO:
                default:
                startTime = findLastMonthStart();
                break;
        }
        return startTime;
    }
    /**
     * 获取结束时间段
     * @param dateSelect
     * @return Long endTime
     * @author lteian
     */
    public Long findEndDate (int dateSelect){
        Long endTime;
        switch (dateSelect){
            case DATE_TODAY:
                endTime = System.currentTimeMillis();
                break;
            case DATE_YESTERDAY:
                endTime = findToday() - 24*60*60*1000;
                break;
            case DATE_LAST_MONTH:
                endTime = findToday();
                break;
            case DATE_LONG_TIME_AGO:
                default:
                endTime = findLastMonthStart();
                break;
        }
        return endTime;
    }
}
