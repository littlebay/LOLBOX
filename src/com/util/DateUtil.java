package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @param String("yyyy-MM-dd HH:mm:ss")
	 * @return Date
	 */
	public Date string2Date(String dateString) {
		String style="yyyy-MM-dd HH:mm:ss";
        if (StrUtil.isBlank(dateString)) return null;
        Date date = new Date();
        SimpleDateFormat strToDate = new SimpleDateFormat(style);
        try {
            date = strToDate.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
	
}
