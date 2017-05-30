/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author zrobe
 */
public class PanelFactory {
    
    private static PanelFactory instance;
    
    private PanelFactory() {}
    
    public static PanelFactory getInstance() {
        if (instance == null) {
            instance = new PanelFactory();
        }
        return instance;
    }
    
    /**
     * Painel para ocupar espaco onde nao ha elementos, mas existe a necessidade da continuidade do organizacao do layout
     * 
     * @param dimension
     * @return 
     */
    public JPanel getEmptyPanel(Dimension dimension) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(dimension);
        panel.setBackground(null);
        return panel;
    }
    
    /**
     * Obter um painel para conter outros componentes
     * 
     * @param dimension
     * @return 
     */
    public JPanel getContainerPanel(Dimension dimension) {
        JPanel panel = getEmptyPanel(dimension);        
        panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        return panel;
    }
    
}
