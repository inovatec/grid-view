/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import br.com.inovatec.grid.view.contract.Field;
import br.com.inovatec.grid.view.values.Styles;
import javax.swing.JCheckBox;

/**
 *
 * @author zrobe
 */
public class Checkbox extends JCheckBox implements Field {

    public Checkbox() {
        this.config();
    }
    
    public Checkbox(String text) {
        super(text);
        this.config();
    }

    public Checkbox(String text, boolean selected) {
        super(text, selected);
        this.config();
    }

    private void config() {
        this.setFont(Styles.FONT_FAMILY);
        this.setBackground(null);
    }
    
}
