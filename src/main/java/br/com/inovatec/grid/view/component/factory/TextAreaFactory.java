/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.TextArea;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import java.awt.Dimension;

/**
 *
 * @author zrobe
 */
public class TextAreaFactory {
    
    private static TextAreaFactory instance;
    
    private TextAreaFactory() {}
    
    public static TextAreaFactory getInstance() {
        if (instance == null) {
            instance = new TextAreaFactory();
        }
        return instance;
    }
    
    public TextArea getTextArea(Dimension dimension) {
        TextArea textArea = new TextArea();
        textArea.setPreferredSize(dimension);
        return textArea;
    }
    
    public TextArea getTextArea(Dimension dimension, int limit) {
        TextArea textArea = new TextArea(limit);
        textArea.setPreferredSize(dimension);
        return textArea;
    }
    
    public TextArea getTextArea(int containerWidth, int lineChilds, Double weight) {
        // Componente
        return getTextArea(ComponentUtils.getTextAreaDimension(containerWidth, lineChilds, weight));
    }
    
    public TextArea getTextArea(int containerWidth, int lineChilds, Double weight, int limit) {
        // Componente
        return getTextArea(ComponentUtils.getTextAreaDimension(containerWidth, lineChilds, weight), limit);
    }
    
}
