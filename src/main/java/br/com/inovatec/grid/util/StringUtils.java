/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.util;

import java.text.ParseException;

/**
 *
 * @author zrobe
 */
public class StringUtils {
    
    public static final String TELEFONE_MASK = "(##) ####-####";
    public static final String CELULAR_MASK = "(##) #####-####";

    public static boolean isValid(String value) {
        return value != null && !value.equals("") && !value.trim().isEmpty();
    }

    public static String formatString(String value, String mask) throws ParseException {
        javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter(mask);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(value);
    }

}
