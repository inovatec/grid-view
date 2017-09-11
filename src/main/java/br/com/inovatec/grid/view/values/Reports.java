/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.values;

/**
 *
 * @author zrobe
 */
public class Reports {
    
    private static final String REPORTS_FILE_PATH = "reports/?.jasper";
    
    public static final String GRADE = REPORTS_FILE_PATH.replace("?", "grade-horarios");
    public static final String TESTE = REPORTS_FILE_PATH.replace("?", "teste");
    
}
