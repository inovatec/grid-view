/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.main;

import br.com.inovatec.grid.entity.Professor;
import br.com.inovatec.grid.view.values.Colors;
import br.com.inovatec.grid.view.values.Cursors;
import br.com.inovatec.grid.view.values.Dimens;
import br.com.inovatec.grid.view.values.Styles;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author zrobe
 */
public class ProfessorCompetenteJLabel extends javax.swing.JLabel {

    private boolean released = true;
    
    public ProfessorCompetenteJLabel(Professor professor) {
        super(professor.getNome());
        this.config();
    }    
    
    /**
     * Configurar classe
     */
    private void config() {
        // Configurar o label de exibição
        setFont(Styles.FONT_FAMILY_BOLD);
        setForeground(Colors.COLOR_WHITE);
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setOpaque(true);
        addMouseListener(new DefaultMouseListener());
        this.release();
    }
    
    public void block() {
        this.released = false;
        setBackground(Colors.COLOR_GREY);
        setBorder(new LineBorder(Colors.COLOR_GREY_BORDER, 1));
    }
    
    public void release() {
        this.released = true;
        setBackground(Colors.COLOR_GREEN);
        setBorder(new LineBorder(Colors.COLOR_GREEN_BORDER, 1));
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }
    
    private class DefaultMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (released) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                setCursor(Cursors.CURSOR_BLOCKED);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
    }
    
}
