/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.reports.provider;

import br.com.inovatec.grid.entity.Escola;
import br.com.inovatec.grid.reports.data.factory.TurmaContainerFactory;
import br.com.inovatec.grid.view.session.Session;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.inovatec.grid.view.values.Reports;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author zrobe
 */
public class ReportsProvider {

    public static JasperPrint generateGradeHorariosReport() {
        // Parametros do relatorio
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ESCOLA", Session.getInstance().getEscola());
        
        // Conexao com os dados
        // JRBeanCollectionDataSource
        JRDataSource dataSource = new JRBeanCollectionDataSource(TurmaContainerFactory.getTurmasContainersByPeriodoCorrente());
        
        // Gerar relatorio
        return generateReport(Reports.GRADE, parameters, dataSource);
    }

    private static JasperPrint generateReport(String reportPath, Map<String, Object> parameters, JRDataSource dataSource) {
        try {
            // Arquivo de Relatorio
            InputStream is = new BufferedInputStream(Reports.class.getClassLoader().getResourceAsStream(reportPath));
            // Retornar relatorio Jasper a partir do inputStream
            return JasperFillManager.fillReport(is, parameters, dataSource);
        } catch (JRException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
