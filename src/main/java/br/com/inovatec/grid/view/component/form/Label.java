/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import javax.swing.JLabel;

/**
 *
 * @author zrobe
 */
public class Label extends JLabel {

    public Label(String text) {
        super(text);
        this.config();
    }

    public Label() {
        this.config();
    }
    
    private void config() {
    }
    
}
