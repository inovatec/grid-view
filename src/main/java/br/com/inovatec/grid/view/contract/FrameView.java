/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.contract;

import javax.swing.JFrame;

/**
 *
 * @author zrobe
 */
public interface FrameView extends View {
    
    /**
     * Obter o dialog interno
     * @return 
     */
    public JFrame getFrame();
    
}
