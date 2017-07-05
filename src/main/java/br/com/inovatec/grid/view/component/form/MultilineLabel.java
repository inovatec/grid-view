/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import br.com.inovatec.grid.view.values.Styles;
import javax.swing.JTextArea;

/**
 *
 * @author zrobe
 */
public class MultilineLabel extends JTextArea {

    private static final long serialVersionUID = 1L;

    public MultilineLabel(String text) {
        super(text);
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
        setFont(Styles.FONT_FAMILY);
        setWrapStyleWord(true);
        setLineWrap(true);
    }
}
