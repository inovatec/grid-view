/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.Label;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class LabelFactory {
    
    private static LabelFactory instance;
    
    private LabelFactory() {}
    
    public static LabelFactory getInstance() {
        if (instance == null) {
            instance = new LabelFactory();
        }
        return instance;
    }
    
    public Label getLabel(String text, Dimension dimension) {
        Label label = new Label(text);
        label.setPreferredSize(dimension);
        label.setFont(Styles.FONT_FAMILY_BOLD);
        return label;
    }
    
    public Label getLabel(String text, int containerWidth, int lineChilds, Double weight) {
        // Componente
        return getLabel(text, ComponentUtils.getLabelDimension(containerWidth, lineChilds, weight));
    }
    
    public Label getWarningLabel(String text, Dimension dimension) {
        Label label = new Label(text);
        label.setPreferredSize(dimension);
        label.setFont(Styles.FONT_FAMILY);
        label.setForeground(Colors.COLOR_FONT_WARNING);
        return label;
    }
    
}
