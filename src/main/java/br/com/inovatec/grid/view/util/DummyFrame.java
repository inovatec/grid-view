/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.util;

import br.com.inovatec.grid.view.MainJFrame;
import br.com.inovatec.grid.view.contract.FrameView;
import br.com.inovatec.grid.view.values.Icons;
import javax.swing.JFrame;

/**
 *
 * @author zrobe
 */
public class DummyFrame extends JFrame implements FrameView {

    private final MainJFrame mainJFrame;
    
    public DummyFrame(String title, MainJFrame mainJFrame) {
        super(title);
        this.mainJFrame = mainJFrame;
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImages(Icons.getAppIcons());
    }

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public void refresh() {
        // Atualizar a janela principal
        this.mainJFrame.refresh();
    }

    @Override
    public void display() {
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    public MainJFrame getMainJFrame() {
        return mainJFrame;
    }
    
}
