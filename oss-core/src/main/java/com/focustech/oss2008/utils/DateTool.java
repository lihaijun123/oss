package com.focustech.oss2008.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.focustech.oss2008.Constants;

/**
 * Utilities for manipulations of strings
 *
 * @version $Revision: 1.3 $ $Date: 2009-01-16 07:28:00 $
 * @author Michael
 */
public class DateTool {
    private static Pattern pDateFormat =
            Pattern.compile("^(\\d{4})[^\\d](\\d{1,2})[^\\d](\\d{1,2})( (\\d{1,2}):(\\d{1,2}):(\\d{1,2})){0,1}$");
    private static Pattern qDateFormat =
            Pattern
                    .compile("^(\\d{1,2})[^\\d](\\d{1,2})[^\\d](\\d{4})( (\\d{1,2}):(\\d{1,2}):(\\d{1,2}) (AM|PM)){0,1}$");
    private static Pattern oDateFormat =
            Pattern.compile("^(\\d{4})(\\d{1,2})(\\d{1,2})((\\d{1,2})(\\d{1,2})(\\d{1,2})){0,1}$");
    private static String[] MONTHS =
            {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                    "November", "December"};
    private static int[] DAY_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] FEB_DAY_OF_MONTH = {29, 28, 28, 28};

    /**
     * Constructor
     */
    public DateTool() {
        super();
    }

    /**
     * 格式化一個(yyyy-MM-dd HH:mm:ss)格式的日期
     *
     * @param time 指定日期時間
     * @return 返回格式化後的字符串
     */
    public static String formatDate(Date time) {
        return formatDate(time, Constants.DATETIMEPATTERN_SEP);
    }

    /**
     * 格式化一個日期到指定的格式
     *
     * @param time 指定日期時間
     * @param format 時間格式
     * @return 返回格式化後的字符串
     */
    public static String formatDate(Date time, String format) {
        String reTime = "";
        if (time != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                reTime = sdf.format(time);
            }
            catch (Exception e) {
            }
        }
        return reTime;
    }

    public static Date getDate(String dateStr, String format) {
        SimpleDateFormat sft = new SimpleDateFormat(format);
        try {
            return sft.parse(dateStr);
        }
        catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 得到給定日期的加(正數)或減(負數)小時後的時間
     *
     * @param time
     * @param iHours
     * @param format
     * @return
     */
    public static String getDateOfHour(Date time, int iHours, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.HOUR, iHours);
        return formatDate(c.getTime(), format);
    }

    @SuppressWarnings("deprecation")
    public static Date formatString2Date4q(String strDateTime) {
        Date date = getCurDate();
        if (strDateTime != null && !"".equals(strDateTime.trim())) {
            Matcher m = qDateFormat.matcher(strDateTime);
            if (m.find()) {
                if (m.group(4) != null) {
                    int addNum = 0;
                    if (m.group(8).equals("AM")) {
                        addNum = 0;
                    }
                    else {
                        addNum = 12;
                    }
                    date =
                            new Date(Integer.parseInt(m.group(3)) - 1900, Integer.parseInt(m.group(1)) - 1, Integer
                                    .parseInt(m.group(2)), Integer.parseInt(m.group(5)) + addNum, Integer.parseInt(m
                                    .group(6)), Integer.parseInt(m.group(7)));
                }
                else {
                    date =
                            new Date(Integer.parseInt(m.group(3)) - 1900, Integer.parseInt(m.group(1)) - 1, Integer
                                    .parseInt(m.group(2)));
                }
            }
        }
        if (date == null) {
            date = getCurDate();
        }
        return date;
    }

    public static void main(String[] ara) {
        try {
            System.out.println(formatDate(checkAndFormatStr2Date("2009-12-31 0:0:0")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到給定日期的加(正數)或減(負數)天數後的時間
     *
     * @param time 給定日期
     * @param iDays 要加還是減多少天
     * @param format 返回日期的格式
     * @return
     */
    public static String getDateOfDay(Date time, int iDays, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, iDays);
        return formatDate(c.getTime(), format);
    }

    /**
     * 得到給定日期的加(正數)或減(負數)天數後的時間
     *
     * @param time
     * @param iDays
     * @return
     */
    public static Date getDateOfDay(Date time, int iDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, iDays);
        return c.getTime();
    }

    /**
     * 得到給定日期的加(正數)或減(負數)周數後的時間
     *
     * @param time 給定日期
     * @param iWeeks 要加還是減多少年
     * @param format 返回日期的格式
     * @return
     */
    public static String getDateOfWeek(Date time, int iWeeks, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.WEEK_OF_YEAR, iWeeks);
        return formatDate(c.getTime(), format);
    }

    /**
     * 得到給定日期的加(正數)或減(負數)年數後的時間
     *
     * @param time 給定日期
     * @param iYears 要加還是減多少年
     * @param format 返回日期的格式
     * @return
     */
    public static String getDateOfYear(Date time, int iYears, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.YEAR, iYears);
        return formatDate(c.getTime(), format);
    }

    /**
     * 得到給定日期的加(正數)或減(負數)月數後的時間
     *
     * @param time 給定日期
     * @param iMonth 要加還是減多少月
     * @param format 返回日期的格式
     */
    public static String getMonthOfYear(Date time, int iMonth, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, iMonth);
        return formatDate(c.getTime(), format);
    }

    /**
     * 得到給定日期的加(正數)或減(負數)月數後的時間
     *
     * @param time 給定日期
     * @param iMonth 要加還是減多少月
     */
    public static Date getMonthOfYear(Date time, int iMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, iMonth);
        return c.getTime();
    }

    /**
     * 得到給定日期的加(正數)或減(負數)分鐘後的時間
     *
     * @param time
     * @param iMinute
     * @return
     */
    public static Date getDateOfMinute(Date time, int iMinute) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MINUTE, iMinute);
        return c.getTime();
    }

    /**
     * 得到給定日期是這年的第幾周
     *
     * @param time 指定日期
     * @return 返回一年中的第幾周
     */
    public static String getWeekOfYear(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(i);
    }

    /**
     * 得到給定日期是所在年的第幾周
     *
     * @param time 指定日期
     * @return 返回一年中的第幾周
     */
    public static String getWeekOfYear(Date time, int iDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.DATE, iDays);
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(i);
    }

    /**
     * @param time
     * @param iWeeks
     * @return
     */
    public static String getWeekOfYearAddWeek(Date time, int iWeeks) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.WEEK_OF_YEAR, iWeeks);
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(i);
    }

    /**
     * @param time
     * @param iMonths
     * @return
     */
    public static String getWeekOfYearAddMonth(Date time, int iMonths) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, iMonths);
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(i);
    }

    /**
     * @param time
     * @param iYears
     * @return
     */
    public static String getWeekOfYearAddYear(Date time, int iYears) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.YEAR, iYears);
        int i = c.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(i);
    }

    /**
     * 得到給定日期是所在月的第幾周
     *
     * @param time 指定的日期
     * @return 返回第幾周的數值
     */
    public static String getWeekOfMonth(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int i = c.get(Calendar.WEEK_OF_MONTH);
        return String.valueOf(i);
    }

    /**
     * 返回所給時間月份的第一個星期五是幾號
     *
     * @param time
     * @return
     */
    public static int getFirstFriOfMonth(Date time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        if (calendar.get(Calendar.DAY_OF_WEEK) > 6) {
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到指定的月有多少天
     *
     * @param time
     * @return
     */
    public static int getDaysOfMonth(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return i;
    }

    /**
     * @param time
     * @param iMonth
     * @return
     */
    public static int getDaysOfMonth(Date time, int iMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, iMonth);
        int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return i;
    }

    /**
     * @param time
     * @param iMonth
     * @param iDays
     * @return
     */
    public static int getDaysOfMonth(Date time, int iMonth, int iDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MONTH, iMonth);
        c.add(Calendar.DATE, iDays);
        int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return i;
    }

    /**
     * 得到某時間格式的時間
     *
     * @param format
     * @return
     */
    public static String getCurDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String theDate = sdf.format(getCurDate());
        return theDate;
    }

    /**
     * 返回當前日期
     *
     * @return
     */
    public static Date getCurDate() {
        return new Date();
    }

    /**
     * 返回yyyyMMdd格式日期
     *
     * @return
     */
    public static String getSystemDate() {
        return getCurDate(Constants.DATEPATTERN_NOSEP);
    }

    /**
     * 返回HHmmss格式時間
     *
     * @return
     */
    public static String getSystemTime() {
        return getCurDate(Constants.TIMEPATTERN_NOSEP);
    }

    /**
     * 返回yyyyMMddHHmmss格式日期時間
     *
     * @return
     */
    public static String getSystemDateTime() {
        return getCurDate(Constants.DATETIMEPATTERN_NOSEP);
    }

    /**
     * 將字符串時間yyyymmddhhmmss格式化成yyyy-mm-dd hh:mm:ss
     *
     * @param strDateTime
     * @return
     */
    public static String formatDateTime(String strDateTime) {
        String strRe = "";
        String year = "";
        String month = "";
        String day = "";
        String hour = "";
        String minute = "";
        String second = "";
        int iLength = strDateTime.length();
        if (iLength == 14) {
            year = strDateTime.substring(0, 4);
            month = strDateTime.substring(4, 6);
            day = strDateTime.substring(6, 8);
            hour = strDateTime.substring(8, 10);
            minute = strDateTime.substring(10, 12);
            second = strDateTime.substring(12, 14);
            strRe = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        }
        return strRe;
    }

    @SuppressWarnings("deprecation")
    public static Date formatString2Date(String strDateTime) {
        Date date = getCurDate();
        if (strDateTime != null && !"".equals(strDateTime.trim())) {
            Matcher m = pDateFormat.matcher(strDateTime);
            if (m.find()) {
                if (m.group(4) != null) {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)),
                                    Integer.parseInt(m.group(7)));
                }
                else {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)));
                }
            }
        }
        if (date == null) {
            date = getCurDate();
        }
        return date;
    }

    @SuppressWarnings("deprecation")
    public static Date formatString2Date4o(String strDateTime) {
        Date date = getCurDate();
        if (strDateTime != null && !"".equals(strDateTime.trim())) {
            Matcher m = oDateFormat.matcher(strDateTime);
            if (m.find()) {
                if (m.group(4) != null) {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)),
                                    Integer.parseInt(m.group(7)));
                }
                else {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)));
                }
            }
        }
        if (date == null) {
            date = getCurDate();
        }
        return date;
    }

    /**
     * 將字符串轉換成Date,如果不能成功轉換,拋出異常
     *
     * @param strDateTime 字符串型的日期
     * @return Date
     * @throws Exception 轉換失敗
     */
    @SuppressWarnings("deprecation")
    public static Date checkAndFormatString2Date(String strDateTime) throws Exception {
        Date date = null;
        if (strDateTime != null && !"".equals(strDateTime.trim())) {
            Matcher m = pDateFormat.matcher(strDateTime);
            if (m.find()) {
                if (m.group(4) != null) {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)),
                                    Integer.parseInt(m.group(7)));
                }
                else {
                    date =
                            new Date(Integer.parseInt(m.group(1)) - 1900, Integer.parseInt(m.group(2)) - 1, Integer
                                    .parseInt(m.group(3)));
                }
            }
        }
        if (date == null) {
            throw new Exception("時間格式錯誤!");
        }
        return date;
    }

    /**
     * 將字符串轉換成Date,如果不能成功轉換,拋出異常
     *
     * @param strDateTime 字符串型的日期
     * @return Date
     * @throws Exception 轉換失敗
     */
    @SuppressWarnings("deprecation")
    public static Date checkAndFormatStr2Date(String strDateTime) throws Exception {
        Date date = null;
        if (strDateTime != null && !"".equals(strDateTime.trim())) {
            Matcher m = pDateFormat.matcher(strDateTime);
            if (m.find()) {
                int year = Integer.parseInt(m.group(1));
                int month = Integer.parseInt(m.group(2));
                int day = Integer.parseInt(m.group(3));
                if (month > 12 || month <= 0) {
                    throw new Exception("月份格式不對!");
                }
                if (day <= 0) {
                    throw new Exception("日期格式不對!");
                }
                if (month != 2 && day > DAY_OF_MONTH[month - 1]) {
                    throw new Exception("日期格式不對!");
                }
                //
                if (month == 2) {
                    if (day > FEB_DAY_OF_MONTH[year % 4]) {
                        throw new Exception("日期格式不對!");
                    }
                }
                if (m.group(4) != null) {
                    int hh = Integer.parseInt(m.group(5));
                    int mm = Integer.parseInt(m.group(6));
                    int ss = Integer.parseInt(m.group(7));
                    if (hh > 23) {
                        throw new Exception("小時格式不對!");
                    }
                    if (mm > 59) {
                        throw new Exception("分格式不對!");
                    }
                    if (ss > 59) {
                        throw new Exception("秒格式不對!");
                    }
                    //
                    date = new Date(year - 1900, month - 1, day, hh, mm, ss);
                }
                else {
                    date = new Date(year - 1900, month - 1, day);
                }
            }
        }
        if (date == null) {
            throw new Exception("時間格式錯誤!");
        }
        return date;
    }

    /**
     * 判斷給定的時間是否合法
     *
     * @param strDate 字符串形式的時間
     * @return true:時間合法;false:時間非法
     */
    public static boolean isValidDate(String strDate) {
        try {
            checkAndFormatString2Date(strDate);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 計算2個日期之間相差的時間（毫秒）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getInterval(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long intervalDate = endTime - startTime;
        return intervalDate;
    }

    /**
     * 提供時間參數(yyyy-mm-dd)計算出與當前時間相差的月數
     */
    public static int getDiffMonths(String invalidDate) {
        int iRe = -1;
        try {
            // 得到失效的月份和Day of month
            int invalidYear = Integer.parseInt(invalidDate.substring(0, 4));
            int invalidMonth = Integer.parseInt(invalidDate.substring(5, 7));
            int invalidDay = Integer.parseInt(invalidDate.substring(8, 10));
            // 構建日歷,來得到當前的月份和Day of month
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(DateTool.getCurDate());
            int curYear = calendar.get(GregorianCalendar.YEAR);
            int curMonth = calendar.get(GregorianCalendar.MONTH) + 1;
            int curDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);
            int theYearDiffer = invalidYear - curYear;
            int theDayDiffer = invalidDay - curDay;
            if (theDayDiffer <= 0) {
                iRe = theYearDiffer * 12 + invalidMonth - curMonth;
            }
            else {
                iRe = theYearDiffer * 12 + invalidMonth - curMonth + 1;
            }
            if (iRe < 0) {
                iRe = 0;
            }
        }
        catch (Exception ex) {
        }
        return iRe;
    }

    /**
     * 取得某個時區的一個時間在另一個時區的對應時間
     *
     * @see Date
     * @see TimeZone
     * @param date originalTimeZoneCode時區的時間
     * @param originalTimeZoneCode 原時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param destinationTimeZoneCode 目標時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param 返回時間的字符串格式 可在Constants文件中找到
     * @return String 字符串形式的時間
     */
    public static String getTimeWithTimeZone(Date date, String originalTimeZoneCode, String destinationTimeZoneCode,
            String dateFormat) {
        // 構造時區對象
        TimeZone originalTimeZone = TimeZone.getTimeZone(originalTimeZoneCode);
        TimeZone destinationTimeZone = TimeZone.getTimeZone(destinationTimeZoneCode);
        Date newDate = new Date(date.getTime() - originalTimeZone.getRawOffset() + destinationTimeZone.getRawOffset());
        return formatDate(newDate, dateFormat);
    }

    /**
     * 取得某個時區的一個時間在另一個時區的對應時間
     *
     * @param strDate 字符串形式的originalTimeZoneCode時區的時間
     * @param originalTimeZoneCode 原時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param destinationTimeZoneCode 目標時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param 返回時間的字符串格式 可在Constants文件中找到
     * @return String 字符串形式的時間
     */
    public static String getTimeWithTimeZone(String strDate, String originalTimeZoneCode,
            String destinationTimeZoneCode, String dateFormat) {
        return getTimeWithTimeZone(formatString2Date(strDate), originalTimeZoneCode, destinationTimeZoneCode,
                dateFormat);
    }

    /**
     * 取得某個時區的一個時間在本地的對應時間
     *
     * @param date originalTimeZoneCode時區的時間
     * @param originalTimeZoneCode 原時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param dateFormat 返回時間的字符串格式 可在Constants文件中找到
     * @return String 字符串形式的時間
     */
    public static String getLocalTimeWithTimeZone(Date date, String originalTimeZoneCode, String dateFormat) {
        return getTimeWithTimeZone(date, originalTimeZoneCode, Calendar.getInstance().getTimeZone().getID(), dateFormat);
    }

    /**
     * 取得某個時區的一個時間在本地的對應時間
     *
     * @param date 字符串形式的originalTimeZoneCode時區的時間
     * @param originalTimeZoneCode 原時區,可用"GMT+08"等方式表示;如果給錯了時區,JVM會自動返回GMT時區
     * @param dateFormat 返回時間的字符串格式 可在Constants文件中找到
     * @return String 字符串形式的時間
     */
    public static String getLocalTimeWithTimeZone(String strDate, String originalTimeZoneCode, String dateFormat) {
        return getTimeWithTimeZone(formatString2Date(strDate), originalTimeZoneCode, Calendar.getInstance()
                .getTimeZone().getID(), dateFormat);
    }

    /**
     * 返回2個時間的年份差值
     *
     * @param startDate 開始時間
     * @param endDate 結束時間
     * @return
     */
    public static int getIntervalYear(Date startDate, Date endDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int intStartYear = calendar.get(Calendar.YEAR);
        //
        calendar.setTime(endDate);
        int intEndYear = calendar.get(Calendar.YEAR);
        //
        return intEndYear - intStartYear;
    }

    /**
     * 取得當前時間的年份
     *
     * @param date 當前時間
     * @return 年份
     * @throws IllegalArgumentException 當前時間為空
     */
    public static String getYearOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    /**
     * 取得當前時間的月份
     *
     * @param date 當前時間
     * @return 月份
     * @throws IllegalArgumentException 當前時間為空
     */
    public static String getMonthOfDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.MONTH) + 1);
    }

    /**
     * 設定所給日期的某個域(年、月、日、時、分、秒)的值.域的名字與Calendar中的相同
     *
     * @param date 給定的時間
     * @param field 所要修改的域(年、月、日、時、分、秒)
     * @param value 修改後的值
     * @see Calendar
     * @return 修改後的時間
     */
    public static Date modifyDateFieldValue(Date date, int field, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(field, value);
        date = c.getTime();
        return date;
    }

    /**
     * 返回所給時間某個域(年、月、日、時、分、秒)的值
     *
     * @param date 給定的時間
     * @param field 所要取得的域(年、月、日、時、分、秒)
     * @return 這個域的值
     */
    public static int getFieldValueOfDate(Date date, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }

    /**
     * 獲得英文日期顯示格式
     *
     * @param date
     * @return
     */
    public static String getShowDateEN(String date) {
        String formatDate = "";
        Date time = formatString2Date(date);
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        formatDate = MONTHS[c.get(Calendar.MONTH)];
        formatDate += " " + c.get(Calendar.YEAR);
        return formatDate;
    }

    /**
     * 格式化一個String類型的日期按照指定格式
     *
     * @throws ParseException
     */
    public static String formatDate(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(time);
        return sdf.format(d);
    }

    /**
     * 返回指定格式的to_date()
     *
     * @param date 時間
     * @param format 格式
     * @return
     */
    public static String toDate(Date date, String format) {
        StringBuffer sb = new StringBuffer();
        sb.append("to_date('").append(DateTool.formatDate(date, format)).append("','").append(format).append("')");
        return sb.toString();
    }

    /**
     * 返回默認格式的to_date
     *
     * @param date
     * @return
     */
    public static String toDate(Date date) {
        StringBuffer sb = new StringBuffer();
        sb.append("to_date('").append(DateTool.formatDate(date, Constants.DATETIMEPATTERN_SEP)).append("','").append(
                Constants.DATETIMEPATTERN_SEP_MI).append("')");
        return sb.toString();
    }

    /**
     * 獲取該參數之前的時間 若當前時間為2008-05-01,傳遞參數為2,返回2008-03-01
     *
     * @param cerMouth月份
     * @return
     */
    public static Date getFindDate(int cerMouth) {
        Date date = null;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -cerMouth);
        c.set(Calendar.DATE, 1);
        date = c.getTime();
        return date;
    }

    /**
     * 判定時間date1是否在時間date2之前 時間格式 2008-9-20 00:00:00
     */
    public static boolean isDateBefore(String date1, String date2) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date1).before(df.parse(date2));
        }
        catch (ParseException e) {
            return false;
        }
    }

    /**
     * 把指定時間的時分秒改為“00:00:00”
     *
     * @param time 指定時間
     * @return 返回修改後的時間
     */
    public static Date convertDateToFirstSecond(Date time) {
        time = modifyDateFieldValue(time, Calendar.HOUR_OF_DAY, 0);
        time = modifyDateFieldValue(time, Calendar.MINUTE, 0);
        time = modifyDateFieldValue(time, Calendar.SECOND, 0);
        return time;
    }

    /**
     * 把指定時間的時分秒改為“00:00:00”
     *
     * @param time 指定時間
     * @return 返回修改後的時間
     * @throws Exception 時間格式不對
     */
    public static Date convertDateToFirstSecond(String time) throws Exception {
        return convertDateToFirstSecond(checkAndFormatStr2Date(time));
    }

    /**
     * 把指定時間的時分秒改為“23:59:59”
     *
     * @param time 指定時間
     * @return 返回修改後的時間
     */
    public static Date convertDateToLastSecond(Date time) {
        time = modifyDateFieldValue(time, Calendar.HOUR_OF_DAY, 23);
        time = modifyDateFieldValue(time, Calendar.MINUTE, 59);
        time = modifyDateFieldValue(time, Calendar.SECOND, 59);
        return time;
    }

    /**
     * 把指定時間的時分秒改為“23:59:59”
     *
     * @param time 指定時間
     * @return 返回修改後的時間
     * @throws Exception 時間格式不對
     */
    public static Date convertDateToLastSecond(String time) throws Exception {
        return convertDateToLastSecond(checkAndFormatStr2Date(time));
    }
}
