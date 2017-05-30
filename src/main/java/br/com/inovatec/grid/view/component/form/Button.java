/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.inovatec.grid.view.component.form;

import br.com.inovatec.grid.view.values.Styles;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author zrobe
 */
public class Button extends JButton implements FocusListener {

    private ButtonType type;
    private boolean transparent;
    private Color backgroundColor, hoverBackgroundColor, pressedBackgroundColor, disabledColor, borderColor, foregroundColor;
    
    public enum ButtonType {
        CIRCLE, OPTION_LEFT, OPTION_RIGHT, OPTION_CENTER;
    }

    public Button() {
    }

    public Button(Color backgroundColor, Color hoverBackgroundColor, Color pressedBackgroundColor, Color disabledColor, Color borderColor, Color foregroundColor, String text) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        this.disabledColor = disabledColor;
        this.borderColor = borderColor;
        this.foregroundColor = foregroundColor;
        this.config();
    }

    public Button(Color backgroundColor, Color hoverBackgroundColor, Color pressedBackgroundColor, Color disabledColor, Color borderColor, Color foregroundColor, String text, Icon icon, boolean transparent) {
        super(text, icon);
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        this.disabledColor = disabledColor;
        this.borderColor = borderColor;
        this.foregroundColor = foregroundColor;
        this.transparent = transparent;
        this.config();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    /**
     * Configurar botao
     */
    private void config() {
        if (this.transparent) {
            this.setBorder(null);
            this.setFont(null);
            this.setOpaque(false);
            this.setBorderPainted(false);
        } else {
            this.setBackground(this.backgroundColor);
            this.setForeground(this.foregroundColor);
            this.setBorder(new javax.swing.border.LineBorder(this.borderColor, 1, false));
            this.setFont(Styles.FONT_FAMILY);
            this.setOpaque(true);
        }
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.addFocusListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!this.transparent) {
            if (getModel().isPressed() || getModel().isSelected()) {
                g.setColor(this.pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(this.hoverBackgroundColor);
            } else if (!getModel().isEnabled()) {
                g.setColor(this.disabledColor);                
            } else {
                g.setColor(this.backgroundColor);
            }
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
    }
    
    public void setActive(boolean active) {
        this.setEnabled(active);
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        this.getModel().setSelected(true);
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.getModel().setSelected(false);
    }
    
}
