/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.factory;

import br.com.inovatec.grid.view.component.form.SelectOneMenu;
import br.com.inovatec.grid.view.component.util.ComponentUtils;
import java.awt.Dimension;
import java.util.List;

/**
 *
 * @author zrobe
 */
public class SelectOneMenuFactory {
    
    private static SelectOneMenuFactory instance;
    
    private SelectOneMenuFactory() {}
    
    public static SelectOneMenuFactory getInstance() {
        if (instance == null) {
            instance = new SelectOneMenuFactory();
        }
        return instance;
    }
    
    public <T> SelectOneMenu<T> getSelectOneMenu(Dimension dimension, List<T> elements) {
        SelectOneMenu<T> selectOneMenu = new SelectOneMenu<>(elements);
        selectOneMenu.setPreferredSize(dimension);
        return selectOneMenu;
    }
    
    /**
     * Obter SelectOneMenu com as configurações de dimensões
     * @param <T>
     * @param containerWidth
     * @param lineChilds
     * @param weight
     * @param elements
     * @return 
     */
    public <T> SelectOneMenu<T> getSelectOneMenu(int containerWidth, int lineChilds, Double weight, List<T> elements) {
        return getSelectOneMenu(ComponentUtils.getSelectOneMenuDimension(containerWidth, lineChilds, weight), elements);
    }
    
}
