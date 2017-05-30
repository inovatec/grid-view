/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.util;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author zrobe
 */
public class DateTimeUtils {
    
    public static String getMinimalFormattedTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public static LocalTime getTime(String strTime) throws DateTimeParseException {
        return LocalTime.parse(strTime);
    }
    
    public static String getDayName(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("PT", "BR"));
    }
    
}
