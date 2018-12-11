package cn.repair.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil{
    public static String now(String pattern){
        if(pattern == null || pattern.equals("")){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }
    public static String now(){
        return now("");
    }
}
