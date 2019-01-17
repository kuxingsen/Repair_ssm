package cn.repair.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil{
    private static String DEFAULT_PATTERN="yyyy-MM-dd HH:mm:ss";
    /**
     * 获取当前时间
     * @param pattern 时间格式
     */
    public static String now(String pattern){
        if(pattern == null || pattern.equals("")){
            pattern = DEFAULT_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

    public static String now(){
        return now("");
    }

    public static LocalDateTime string2date(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return LocalDateTime.parse(dateTime,formatter);
    }

    public static String date2string(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        return dateTime.format(formatter);
    }
}
