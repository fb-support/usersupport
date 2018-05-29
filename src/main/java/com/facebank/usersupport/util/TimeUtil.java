package com.facebank.usersupport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: yaozun
 * @Date: 2018/4/12 0012 09:48
 * @Description:
 */
public class TimeUtil {
    public static Date LongToDate(Long time,SimpleDateFormat format){
        Date timeDate = null;
        String timeStr = format.format(time);
        if (timeStr!=""&&timeStr!=null){
            try {
                timeDate = format.parse(timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return timeDate;
    }
}
