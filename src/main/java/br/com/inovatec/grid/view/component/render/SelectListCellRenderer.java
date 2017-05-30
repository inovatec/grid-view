/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.render;

import br.com.inovatec.grid.view.contract.Selectable;
import br.com.inovatec.grid.view.values.Dimens;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author zrobe
 * @param <T>
 */
public class SelectListCellRenderer<T extends Selectable> implements ListCellRenderer<T> {

    private final Border insetBorder;
    private final DefaultListCellRenderer defaultRenderer;

    public SelectListCellRenderer() {
        this.insetBorder = new EmptyBorder(0, Dimens.DEFAULT_PADDING, 0, Dimens.DEFAULT_PADDING);
        this.defaultRenderer = new DefaultListCellRenderer();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends T> list, T value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) this.defaultRenderer
                .getListCellRendererComponent(list, value != null ? value.getLabel() : "Selecione um item", index, isSelected,
                        cellHasFocus);
        renderer.setBorder(this.insetBorder);
        return renderer;
    }

}
