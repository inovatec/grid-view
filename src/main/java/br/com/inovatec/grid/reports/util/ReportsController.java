/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.reports.util;

import br.com.inovatec.grid.reports.provider.ReportsProvider;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author zrobe
 */
public class ReportsController {

    public static void showGradeHorarios() {
        showReport(ReportsProvider.generateGradeHorariosReport());
    }

    private static void showReport(JasperPrint jasperPrint) {
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewer.setVisible(true);
    }

}
