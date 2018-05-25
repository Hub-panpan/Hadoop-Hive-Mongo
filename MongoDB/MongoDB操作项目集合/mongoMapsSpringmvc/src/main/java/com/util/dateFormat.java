package com.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class dateFormat {
    private  static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date formatToSqlDate(String date){
        java.util.Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new java.sql.Date(d.getTime());

//        return java.sql.Date.valueOf(df.format(date));
    }

    public static java.util.Date formaToUtilDate(String date) throws ParseException {
        return df.parse(date);
    }
    public static String sqlDateToString(Date date){
        return df.format(date);
    }
}
