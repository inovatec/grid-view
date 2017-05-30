/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.render;

import br.com.inovatec.grid.view.values.Dimens;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author zrobe
 */
public class DataTableCellRender extends DefaultTableCellRenderer {

    private final Border padding = BorderFactory.createEmptyBorder(Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING, Dimens.DEFAULT_MIN_PADDING);

    public DataTableCellRender() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), this.padding));
        return this;
    }

}
