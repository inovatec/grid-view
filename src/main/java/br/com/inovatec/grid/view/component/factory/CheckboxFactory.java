/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.Checkbox;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class CheckboxFactory {
    
    private static CheckboxFactory instance;
    
    private CheckboxFactory() {}
    
    public static CheckboxFactory getInstance() {
        if (instance == null) {
            instance = new CheckboxFactory();
        }
        return instance;
    }
    
    public Checkbox getCheckbox(String text, Dimension dimension) {
        Checkbox checkbox = new Checkbox(text);
        checkbox.setPreferredSize(dimension);
        checkbox.setFont(Styles.FONT_FAMILY_BOLD);
        return checkbox;
    }
    
}
