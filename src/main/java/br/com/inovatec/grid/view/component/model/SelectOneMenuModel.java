/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.model;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author zrobe
 * @param <T>
 */
public class SelectOneMenuModel<T> extends DefaultComboBoxModel<T>{

    public SelectOneMenuModel(T[] items) {
        super(items);
        insertElementAt(null, 0);
        setSelectedItem(null);
    }

    @Override
    public T getSelectedItem() {
        return (T) super.getSelectedItem();
    }
    
}
