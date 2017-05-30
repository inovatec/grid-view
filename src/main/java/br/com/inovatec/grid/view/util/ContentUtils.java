/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.util;

/**
 *
 * @author zrobe
 */
public class ContentUtils {
    
    public static int doubleToInt(double value) {
        return new Double(value).intValue();
    }
    
    public static String integerToString(Integer value) {
        if (value == null) {
            value = 0;
        }
        return Integer.toString(value);
    }
    
    public static int stringToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException ex) {
            return 0;
        }
    }
    
}
