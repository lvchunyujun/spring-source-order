package com.springdemo.common;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jinjingcheng .
 * On 13-11-18 下午5:50 .
 * To chang this template use  File | Setting | File and Code Templates | Includes
 * On IDEA V130.1105
 */
public class DateTimeUtils {
    /**
     * @param df 日期格式 可不传
     * @return
     */
    public static final String dfstr = "yy年mm月dd日";
    public static final String df = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH = "yyyy_MM";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";


    public static Date getFormatDate(String time, String format){
        SimpleDateFormat fmtDate = new SimpleDateFormat(format);
        Date date =new Date();
        try {
            date = fmtDate.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Timestamp getCreateTimes() {
        Date date = new Date();

        String aFmtDate = "yyyy-MM-dd HH:mm:ss";
        Format fmtDate = new SimpleDateFormat(aFmtDate);
        String strRtn = null;
        try {
            strRtn = fmtDate.format(date);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        Timestamp currentTime = Timestamp.valueOf(strRtn);
        return currentTime;
    }

    public static String getCreateTime() {
        return System.currentTimeMillis() + "";
    }

    public static long getCreateTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * by jinjingcheng
     * 日期格式字符串 转时间戳格式字符串
     *
     * @param dateString
     * @return
     */
    public static String dateStringtoTimestampString(String dateString) {
        String timestampString = "";
        if (StringUtils.isNotBlank(dateString)) {
            timestampString = StringToDate(dateString).getTime() + "";
        }

        return timestampString;
    }

    /**
     * by jinjingcheng
     * 时间戳转日期格式
     *
     * @param timestampString
     * @return
     */
    public static String timestampStringtoDateString(String timestampString, String... fm) {
//        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat fm2 = null;
        String sdf = "yyyy-MM-dd HH:mm:ss";
        if (fm != null && fm.length > 0) {
            sdf = fm[0];
        }
        fm2 = new SimpleDateFormat(sdf);
        long unixLong = 0;
        String date = "";
        try {
            unixLong = Long.parseLong(timestampString);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("String转换Long错误，请确认数据可以转换！timestampString：" + timestampString);
        }
        try {
//            date = fm1.format(unixLong);
            date = fm2.format(unixLong);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("String转换Date错误，请确认数据可以转换！unixLong：" + unixLong);
        }
        return date;
    }

    /**
     * 时间戳字符串 转日期对象
     *
     * @param timestampString
     * @param fm
     * @return
     */
    public static Date timestampStringtoDate(String timestampString, String... fm) {
        String date = timestampStringtoDateString(timestampString, fm);
        return StringToDate(date, fm);
    }

    /**
     * timestamp格式字符串 转timestamp对象 默认格式 yyyy-MM-dd hh:mm:ss
     *
     * @return timestamp对象
     */
    public static Timestamp timestampStringtoTimestamp(String timestampString, String... fm) {
        Timestamp timestamp = null;
        if (StringUtils.isNotBlank(timestampString)) {
            timestamp = stringToTimestamp(timestampStringtoDateString(timestampString, fm));
        }
        return timestamp;
    }

    /**
     * 取得创建时间时间戳
     *
     * @return 创建时间时间戳
     */
    public static String getCreateTimeStr() {
        String createTime = "";
        long now = new Date().getTime();
        createTime = String.valueOf(now);
        return createTime;
    }

    /**
     * 时间戳格式转换日期格式字符串
     *
     * @param time
     * @return
     */
    public static String dateFormat(Timestamp time, String... dateFormat) {
        Date date = time;
        String applyTime = null;
        DateFormat str = null;
        if (dateFormat == null || dateFormat.length == 0) {
            str = new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
            str = new SimpleDateFormat(dateFormat[0]);
        }

        try {
            applyTime = str.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applyTime;
    }

    /**
     * 汇付宝日期格式转换
     *
     * @param
     * @return
     */

    public static String dateFormat(Timestamp time) {

        Date date = time;
        String applyTime = null;
        DateFormat str = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            applyTime = str.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applyTime;
    }


    public static String dateFormat(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        String dateString = "";
        try {
            dateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }


    /**
     * 取得日期差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static long diffDate(Date date1, Date date2) {
        long diff = 0;
        diff = date1.getTime() - date2.getTime();
        diff = diff / 1000 / 60 / 60 / 24;
        return diff;
    }

    /**
     * 取得日期差
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static long diffDate(long date1, long date2) {
        long diff = 0;
        diff = date1 - date2;
        diff = diff / 1000 / 60 / 60 / 24;
        return diff;
    }

    /**
     * 取得日期差 单位为妙
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static long diffSecond(Date date1, Date date2) {
        long diff = 0;
        diff = date1.getTime() - date2.getTime();
        diff = diff / 1000;
        return diff;
    }

    /**
     * shilihong
     * 取得天数差
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(String smdate, String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long between_days = 0;
        try{
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("String转换Long错误，请确认数据可以转换！between_days：" + between_days);
        }
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 字符型时间转为时间类型 zhangxinliang
     *
     * @param time
     * @return
     */
    public static Date StringToDate(String time, String... fm) {
        Date date = new Date();
        String sdf = "yyyy-MM-dd HH:mm:ss";
        if (fm != null && fm.length > 0) {
            sdf = fm[0];
        }
        DateFormat str = new SimpleDateFormat(sdf);
        try {
            date = str.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * zhangxinliang
     * 字符型时间转为Timestamp类型
     *
     * @param time
     * @return
     */
    public static Timestamp stringToTimestamp(String time) {
        String sdf = "yyyy-MM-dd HH:mm:ss";
        if (time != null && time.length() == 10) {  //格式为 "1986-03-20"
            sdf = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(sdf);
        try {
            Timestamp ts = new Timestamp(format.parse(time).getTime());
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * zhangxinliang
     * 字符型时间转为Timestamp类型
     *
     * @param time
     * @return
     */
    public static Timestamp stringToTimestamp(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Timestamp ts = new Timestamp(sdf.parse(time).getTime());
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * zhangxinliang
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * zhangxinliang
     *
     * @return
     */
    public static String getDateToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");//可以方便地修改日期格式
        Date now = new Date();
        String hehe = dateFormat.format(now);
        return hehe;
    }


    /**
     * 时间将字符串转为时间戳
     *
     * @param user_time
     * @return
     */
    public static String getTime(String user_time) {
        String re_time = null;
        String str = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            str = String.valueOf(l);
            //   re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getTime999(String user_time){
        Calendar calendar = Calendar.getInstance();
        Date date= DateTimeUtils.getFormatDate(user_time,DateTimeUtils.df);;
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return String.valueOf(calendar.getTimeInMillis());
    }




    /**
     * 增加日期
     *
     * @param millis 日期的毫秒数
     * @param field  年、月、日 如 Calendar.MONTH
     * @param i      增加的时间
     * @return
     */
    public static long addDate(long millis, int field, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(field, i);
        return calendar.getTimeInMillis();
    }

    /**
     * 增加日期
     *
     * @param date  当前时间
     * @param field 年、月、日 如 Calendar.MONTH
     * @param i     增加的时间
     * @return
     */
    public static Date addDate(Date date, int field, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, i);
        return calendar.getTime();
    }

    /**
     * zhangxinliang
     *
     * @return
     */
    public static String TimeTest() {
        Long time = Calendar.getInstance().getTimeInMillis();
        return Long.toString(time);
    }


    /**
     * 时间戳字符串转换为时间字符串
     *
     * @param time
     * @return
     */
    public static String TimetoTimeStamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(time)));
        return sd;

    }

    /**
     * 取得一天起始时间
     *
     * @param date 日期
     * @return 起始时间
     */
    public static long getDateStartTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 取得一天结束时间
     *
     * @param date 日期
     * @return 结束时间
     */
    public static long getDateEndTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 取得一月的起始时间
     *
     * @param date 日期
     * @return 起始时间
     */
    public static long getMonthStartTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 取得一月的结束时间
     *
     * @param date 日期
     * @return 起始时间
     */
    public static long getMonthEndTime(long date) {
        date = getMonthStartTime(date);
        date = addDate(date,Calendar.MONTH,1);
        date = addDate(date, Calendar.MILLISECOND, -1);
        return date;
    }

    /**
     * 取当月的上个月的年月
     * zhangxinliang
     *
     * @return
     */
    public static String getOnYearString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String first = format.format(c.getTime());
        return first;
    }

    /**
     * 取当月的上一个月的月份
     * zhangxinliang
     *
     * @return
     */
    public static String getlastMonthString() {
        SimpleDateFormat format = new SimpleDateFormat("M");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String first = format.format(c.getTime());
        return first;
    }


    /**
     * Last month first day
     * 取当月上个月的第一天00:00:00 例如2014-06-01 00:00:00
     *
     * @return
     */
    public static String getLastMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first_prevM = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first_prevM).append(" 00:00:00");
        day_first_prevM = str.toString();
        String monthStart = getTime(day_first_prevM);
        return monthStart;
    }

    /**
     * Last day of the last month
     * 取当月上个月的最后一天00:00:00 例如2014-06-30 23:59:59
     *
     * @return
     */
    public static String getLastDayOfTheLastMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String day_end_prevM = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_end_prevM).append(" 23:59:59");
        day_end_prevM = endStr.toString();
        String monthEnd = getTime(day_end_prevM);
        return monthEnd;
    }



    /**
     * 根据传过来的月份取得这个月份的第一天的00：00：00
     * @param number 月份从1开始
     * @return
     */
    public static String getFirstDayOfMonth(int number) {
        int month=number-1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        String monthStartTime = getTime(df.format(cal.getTime()));
        return  monthStartTime;
    }



    /**
     * 根据传过来的月份取得这个月份的最后一天的23:59:59
     * @param number 月份从1开始
     * @return
     */
    public static String getLastDayOfMonth(int number) {
        int month=number-1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        String monthEndTime = getTime(df.format(cal.getTime()));
        return  monthEndTime;

    }

    /**
     * 取得当月第一天的开始时间
     * @return
     */
    public static String getMonthStartTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println(first);
        String monthStart=getTime(first);
        return  monthStart;
    }
    /*
      获取增减天数的当天开始时间
       */
    public static String getDateAddTime(int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return getTime(format.format(calendar.getTime()));
    }
    /**
     * 取得当月第一天的开始时间
     * @return
     */
    public static String getMonthFristDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, -6);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println(first);
        String monthStart=getTime(first);
        return  monthStart;
    }

    /**
     * 取得当月最后一天的结束时间
     * @return
     */
    public static String getMonthEndTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        String monthEnd=getTime(last);
        return  monthEnd;
    }

    /**
     * 当前时间减去2天
     * @return
     */
    public static Date getDateTime(){
    SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date beginDate = new Date();
    Calendar date = Calendar.getInstance();
    date.setTime(beginDate);
    date.set(Calendar.DATE, date.get(Calendar.DATE) - 2);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    /**
     * 当前时间加2天
     * @return
     */
    public static String getTwoDateTime(Date twodate){
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar date = Calendar.getInstance();
        date.setTime(twodate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + 2);
        String last = dft.format(date.getTime());
        return last;
    }

    /**
     * 把字符串时间如:2014-11-04 00:55:55 转为日期时间
     * @param date
     * @return
     */
    public static Date getStringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    /**
     * 取得当天的起始时间
     * 格式：2015-04-24 00:00:00
     * @return
     */
    public static Timestamp getTodayStartTime() {
        Date date = new Date();

        String aFmtDate = "yyyy-MM-dd 00:00:00";
        Format fmtDate = new SimpleDateFormat(aFmtDate);
        String strRtn = null;
        try {
            strRtn = fmtDate.format(date);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        Timestamp currentTime = Timestamp.valueOf(strRtn);
        return currentTime;
    }
    /**
     * 取得当天的结束时间
     * 格式：2015-04-24 23:59:59
     * @return
     */
    public static Timestamp getTodayEndTime() {
        Date date = new Date();

        String aFmtDate = "yyyy-MM-dd 23:59:59";
        Format fmtDate = new SimpleDateFormat(aFmtDate);
        String strRtn = null;
        try {
            strRtn = fmtDate.format(date);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        Timestamp currentTime = Timestamp.valueOf(strRtn);
        return currentTime;
    }

    /**
     * 得到当前日期 一定时间格式aFmtDate的日期
     * String aFmtDate = "yyyy-MM-dd 09:00:00";
     * @param aFmtDate
     * @return  格式：2015-05-24 9:00:00
     */
    public static Timestamp getNowFmtTime(String aFmtDate ) {
        Date date = new Date();
        Format fmtDate = new SimpleDateFormat(aFmtDate);
        String strRtn = null;
        try {
            strRtn = fmtDate.format(date);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        Timestamp currentTime = Timestamp.valueOf(strRtn);
        return currentTime;
    }


    /**
     * 获取当年的第一天
     * @param
     * @return
     */
    public static long getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @param
     * @return
     */
    public static long getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static long getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static long getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取两个日历的月份之差
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static int getMonthsOfAge(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        start.setTime(startDate);
        return (start.get(Calendar.YEAR) - end
                .get(Calendar.YEAR))* 12+ start.get(Calendar.MONTH)
                - end.get(Calendar.MONTH);
    }


    /**
     * 获取到昨天当前时间的时间戳
     */
    public static long yesterdayTimestamp() {
        Calendar yesterdayCalender = new GregorianCalendar();
        yesterdayCalender.setTimeInMillis(System.currentTimeMillis());
        yesterdayCalender.add(Calendar.DAY_OF_MONTH, -1);
        return yesterdayCalender.getTimeInMillis();
    }


    public static void main(String[] arg) throws ParseException {

        System.out.println(System.currentTimeMillis());
        System.out.println(addDate(System.currentTimeMillis(),Calendar.HOUR,-2)+"");

    }

}
