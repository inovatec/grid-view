/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.util;

import br.com.inovatec.grid.view.contract.FrameView;
import java.awt.event.WindowEvent;

/**
 *
 * @author zrobe
 */
public class DefaultWindowAdapter extends java.awt.event.WindowAdapter {

    private final FrameView frameView;

    public DefaultWindowAdapter(FrameView frameView) {
        this.frameView = frameView;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frameView.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

}
