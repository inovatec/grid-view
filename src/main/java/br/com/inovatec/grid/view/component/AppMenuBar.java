/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component;

import br.com.inovatec.grid.view.values.Colors;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JMenuBar;

/**
 *
 * @author zrobe
 */
public class AppMenuBar extends JMenuBar {

    public AppMenuBar() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Colors.COLOR_MENU_BAR);
        g2d.fillRect(0, 0, getWidth(), getHeight());

    }
}
