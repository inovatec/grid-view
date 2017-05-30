/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author zrobe
 */
public abstract class ButtonActionListener implements ActionListener, KeyListener {
    
    public abstract void action();

    @Override
    public void actionPerformed(ActionEvent e) {
        this.action();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // find ENTER key press
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.action();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
