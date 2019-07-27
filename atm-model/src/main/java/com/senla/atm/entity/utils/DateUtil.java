package com.senla.atm.entity.utils;

import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static Logger logger = Logger.getLogger(DateUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");

    public static Date convertStringToDate(String string) {
        try {
            if (!string.equals("null")){
                return sdf.parse(string);
            }
            return null;
        } catch (ParseException e) {
            logger.error("Parse date error", e);
            return null;
        }
    }

    public static String convertDateToString(Date date) {
        try {
            if (date != null){
                return sdf.format(date);
            }
            return null;
        } catch (Exception e){
            logger.error("Parse date error", e);
            return null;
        }
    }
}